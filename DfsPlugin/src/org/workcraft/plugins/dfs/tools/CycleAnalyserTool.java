package org.workcraft.plugins.dfs.tools;

import org.workcraft.dom.Node;
import org.workcraft.dom.visual.SizeHelper;
import org.workcraft.dom.visual.VisualComponent;
import org.workcraft.gui.graph.tools.AbstractGraphEditorTool;
import org.workcraft.gui.graph.tools.Decoration;
import org.workcraft.gui.graph.tools.Decorator;
import org.workcraft.gui.graph.tools.GraphEditor;
import org.workcraft.gui.propertyeditor.PropertyEditorTable;
import org.workcraft.plugins.dfs.VisualDelayComponent;
import org.workcraft.plugins.dfs.VisualDfs;
import org.workcraft.util.GUI;
import org.workcraft.util.Hierarchy;
import org.workcraft.util.IntDocument;
import org.workcraft.util.graph.cycle.ElementaryCyclesSearch;
import org.workcraft.workspace.WorkspaceEntry;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class CycleAnalyserTool extends AbstractGraphEditorTool {
    // Infinity symbol in UTF-8 encoding (avoid inserting UTF symbols directly in the source code).
    public static final char INFINITY_SYMBOL = 0x221E;

    private static final int COLUMN_THROUGHPUT = 0;
    private static final int COLUMN_TOKEN = 1;
    private static final int COLUMN_DELAY = 2;
    private static final int COLUMN_CYCLE = 3;

    private VisualDfs dfs;
    private ArrayList<Cycle> cycles;
    private double minDelay;
    private double maxDelay;
    protected Cycle selectedCycle = null;
    private int cycleCount = 10;

    protected JPanel controlPanel;
    protected JScrollPane infoPanel;
    private JTable cycleTable;
    private JLabel cycleCountLabel;
    private JPanel panel;

    @Override
    public JPanel getControlsPanel(final GraphEditor editor) {
        if (panel != null) {
            return panel;
        }
        controlPanel = new JPanel();
        cycleTable = new JTable(new CycleTableModel());
        cycleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumnModel columnModel = cycleTable.getColumnModel();
        TableColumn throughputColumn = columnModel.getColumn(COLUMN_THROUGHPUT);
        throughputColumn.setPreferredWidth(Math.round(throughputColumn.getPreferredWidth() * 1.6f));
        cycleTable.setRowHeight(SizeHelper.getComponentHeightFromFont(cycleTable.getFont()));
        cycleTable.setDefaultRenderer(Object.class, new CycleTableCellRenderer());
        cycleTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        cycleTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = cycleTable.getSelectedRow();
                if ((cycles != null) && (selectedRow >= 0) && (selectedRow < cycles.size())) {
                    Cycle curCycle = cycles.get(selectedRow);
                    if (selectedCycle != curCycle) {
                        selectedCycle = curCycle;
                    } else {
                        selectedCycle = null;
                        cycleTable.clearSelection();
                    }
                    editor.repaint();
                    editor.requestFocus();
                }
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
            }
            @Override
            public void mousePressed(MouseEvent arg0) {
            }
            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
        infoPanel = new JScrollPane(cycleTable);

        final JTextField cycleCountText = new JTextField();
        Dimension dimension = cycleCountText.getPreferredSize();
        dimension.width = 40;
        cycleCountText.setPreferredSize(dimension);
        cycleCountText.setDocument(new IntDocument(3));
        cycleCountText.setText(String.valueOf(cycleCount));
        cycleCountText.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        cycleCount = Integer.parseInt(cycleCountText.getText());
                        resetSelectedCycle(editor);
                    } catch (NumberFormatException e) {
                        cycleCountText.setText(String.valueOf(cycleCount));
                    }
                } else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cycleCountText.setText(String.valueOf(cycleCount));
                }
            }
            @Override
            public void keyReleased(KeyEvent arg0) {
            }
            @Override
            public void keyTyped(KeyEvent arg0) {
            }
        });

        cycleCountText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                cycleCountText.setText(String.valueOf(cycleCount));
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                cycleCount = Integer.parseInt(cycleCountText.getText());
                resetSelectedCycle(editor);
            }
        });

        cycleCountLabel = new JLabel();
        cycleCountLabel.setText("Cycle count:");
        cycleCountLabel.setLabelFor(cycleCountText);

        controlPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        controlPanel.add(cycleCountLabel);
        controlPanel.add(cycleCountText);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(controlPanel, BorderLayout.PAGE_START);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(0, 0));
        return panel;
    }

    @Override
    public void activated(final GraphEditor editor) {
        super.activated(editor);
        dfs = (VisualDfs) editor.getModel();
        cycleTable.clearSelection();
        selectedCycle = null;
        cycles = findCycles();
        if ((cycles != null) && (cycleCountLabel != null)) {
            cycleCountLabel.setText("Cycle count (out of " + cycles.size() + "):");
        }
    }

    @Override
    public void deactivated(final GraphEditor editor) {
        super.deactivated(editor);
        cycles = null;
        selectedCycle = null;
        dfs = null;
        cycleTable.clearSelection();
    }

    @Override
    public void setPermissions(final GraphEditor editor) {
        WorkspaceEntry we = editor.getWorkspaceEntry();
        we.setCanModify(false);
        we.setCanSelect(false);
        we.setCanCopy(false);
    }

    @Override
    public String getLabel() {
        return "Cycle analyser";
    }

    @Override
    public int getHotKeyCode() {
        return KeyEvent.VK_A;
    }

    @Override
    public Icon getIcon() {
        return GUI.createIconFromSVG("images/dfs-tool-cycle_analysis.svg");
    }

    @Override
    public Decorator getDecorator(final GraphEditor editor) {
        return new Decorator() {
            @Override
            public Decoration getDecoration(Node node) {
                if (node instanceof VisualDelayComponent) {
                    if (selectedCycle == null) {
                        double delay = ((VisualDelayComponent) node).getReferencedDelayComponent().getDelay();
                        double range = maxDelay - minDelay;
                        double offset = delay - minDelay;
                        final Color fgColor = (range > 0 &&  offset > 0.8 * range) ? Color.RED : null;
                        return new Decoration() {
                            @Override
                            public Color getColorisation() {
                                return fgColor;
                            }
                            @Override
                            public Color getBackground() {
                                return null;
                            }
                        };
                    } else if (selectedCycle.components.contains(node)) {
                        double delay = selectedCycle.getEffectiveDelay((VisualDelayComponent) node);
                        double range = selectedCycle.maxDelay - selectedCycle.minDelay;
                        double offset = delay - selectedCycle.minDelay;
                        int bgIintencity = 150;
                        if (range > 0) {
                            bgIintencity = (int) (bgIintencity + (255 - bgIintencity) * offset / range);
                        }
                        final Color fgColor = (range > 0 &&  offset > 0.8 * range) ? Color.RED : null;
                        final Color bgColor = new Color(bgIintencity, 0, 0);
                        return new Decoration() {
                            @Override
                            public Color getColorisation() {
                                return fgColor;
                            }
                            @Override
                            public Color getBackground() {
                                return bgColor;
                            }
                        };
                    }
                }
                return null;
            }
        };
    }

    private ArrayList<Cycle> findCycles() {
        ArrayList<Cycle> result = new ArrayList<>();
        // Update global min and max delay values
        Collection<VisualDelayComponent> allComponents = Hierarchy.getDescendantsOfType(dfs.getRoot(), VisualDelayComponent.class);
        boolean first = true;
        for (VisualDelayComponent c: allComponents) {
            double delay = c.getReferencedDelayComponent().getDelay();
            if (first || minDelay > delay) {
                minDelay = delay;
            }
            if (first || maxDelay < delay) {
                maxDelay = delay;
            }
            first = false;
        }
        // Prepare temporary node array and adjacency matrix
        int size = allComponents.size();
        VisualComponent[] tmpComponents = allComponents.toArray(new VisualComponent[size]);
        boolean[][] adjMatrix = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            HashSet<Node> preset = new HashSet<>(dfs.getPreset(tmpComponents[i]));
            HashSet<Node> postset = new HashSet<>(dfs.getPostset(tmpComponents[i]));
            for (int j = i + 1; j < size; j++) {
                adjMatrix[i][j] = postset.contains(tmpComponents[j]);
                adjMatrix[j][i] = preset.contains(tmpComponents[j]);
            }
        }
        // Calculate simple cycles and process the results
        ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(adjMatrix, tmpComponents);
        List tmpCycles = ecs.getElementaryCycles();
        for (int i = 0; i < tmpCycles.size(); i++) {
            List tmpCycle = (List) tmpCycles.get(i);
            String toString = "";
            LinkedHashSet<VisualDelayComponent> components = new LinkedHashSet<>();
            for (int j = 0; j < tmpCycle.size(); j++) {
                VisualDelayComponent component = (VisualDelayComponent) tmpCycle.get(j);
                if (toString.length() > 0) {
                    toString += Character.toString((char) 0x2192); // arrow symbol
                }
                toString += dfs.getMathModel().getNodeReference(component.getReferencedComponent());
                components.add(component);
            }
            Cycle cycle = new Cycle(dfs, components);
            result.add(cycle);
        }
        Collections.sort(result);
        return result;
    }

    private void resetSelectedCycle(final GraphEditor editor) {
        selectedCycle = null;
        cycleTable.tableChanged(null);
        editor.repaint();
    }

    @SuppressWarnings("serial")
    private final class CycleTableModel extends AbstractTableModel {
        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int column) {
            String result;
            switch (column) {
            case COLUMN_CYCLE:
                result = "Cycle";
                break;
            case COLUMN_TOKEN:
                result = "Tokens";
                break;
            case COLUMN_DELAY:
                result = "Delay";
                break;
            case COLUMN_THROUGHPUT:
                result = "Throughput";
                break;
            default:
                result = "";
                break;
            }
            return result;
        }

        @Override
        public int getRowCount() {
            if (cycles != null) {
                return Math.min(cycleCount, cycles.size());
            }
            return 0;
        }

        @Override
        public Object getValueAt(int row, int col) {
            Object result = null;
            Cycle cycle = cycles.get(row);
            if (cycle != null) {
                switch (col) {
                case COLUMN_THROUGHPUT:
                    if (cycle.totalDelay == 0) {
                        result = Character.toString(INFINITY_SYMBOL);
                    } else {
                        result = new DecimalFormat("#.###").format(cycle.throughput);
                    }
                    break;
                case COLUMN_TOKEN:
                    result = cycle.tokenCount;
                    break;
                case COLUMN_DELAY:
                    result = new DecimalFormat("#.###").format(cycle.totalDelay);
                    break;
                case COLUMN_CYCLE:
                    result = cycle.toString();
                    break;
                default:
                    result = null;
                    break;
                }
            }
            return result;
        }
    }

    @SuppressWarnings("serial")
    private final class CycleTableCellRenderer implements TableCellRenderer {
        private final JLabel label = new JLabel() {
            @Override
            public void paint(final Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                super.paint(g);
            }
        };

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            label.setBorder(PropertyEditorTable.BORDER_RENDER);
            if ((cycles != null) && (row >= 0) && (row < cycles.size())) {
                label.setText(value.toString());
                label.setToolTipText(cycles.get(row).toString());
            }
            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
            } else {
                label.setBackground(table.getBackground());
            }
            return label;
        }
    }

}
