/*
 *
 * Copyright 2008,2009 Newcastle University
 *
 * This file is part of Workcraft.
 *
 * Workcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Workcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Workcraft.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.workcraft.gui.workspace;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.workcraft.Framework;
import org.workcraft.PluginInfo;
import org.workcraft.Tool;
import org.workcraft.dom.Model;
import org.workcraft.exceptions.OperationCancelledException;
import org.workcraft.exceptions.PluginInstantiationException;
import org.workcraft.util.ListMap;
import org.workcraft.util.Pair;
import org.workcraft.util.Tools;
import org.workcraft.workspace.FileHandler;
import org.workcraft.workspace.Workspace;
import org.workcraft.workspace.WorkspaceEntry;
import org.workcraft.workspace.WorkspaceTree;

public class WorkspacePopupProvider {
	private Framework framework;
	private WorkspaceWindow wsWindow;

	public WorkspacePopupProvider(WorkspaceWindow wsWindow) {
		this.framework = wsWindow.getWorkspace().getFramework();
		this.wsWindow = wsWindow;
	}

	public JPopupMenu getPopup(final Path<String> path) {
			JPopupMenu popup = new JPopupMenu();

			final HashMap<JMenuItem, FileHandler> handlers = new HashMap<JMenuItem, FileHandler>();
			final HashMap<JMenuItem, Tool> tools = new HashMap<JMenuItem, Tool>();

			final Workspace workspace = framework.getWorkspace();

			final File file = workspace.getFile(path);

			if(file.isDirectory())
			{
				popup.addSeparator();
				final JMenuItem miLink = new JMenuItem("Link external files or directories...");
				miLink.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent e) {
						wsWindow.addToWorkspace(path);
					}
				});
				popup.add(miLink);
			}

			if (WorkspaceTree.isLeaf(workspace, path))
			{
				if (file.exists()) {
					// add WorkspaceEntry menu items
					PluginInfo[] handlersInfo = framework.getPluginManager()
					.getPluginsImplementing(FileHandler.class.getName());

					for (PluginInfo info : handlersInfo)
						try {
							FileHandler handler = (FileHandler) framework.getPluginManager().getSingleton(info);

							if (!handler.accept(file))
								continue;
							JMenuItem mi = new JMenuItem(info.getDisplayName());
							handlers.put(mi, handler);
							mi.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									handlers.get(e.getSource()).execute(file, framework);
								}
							});
							popup.add(mi);
						} catch (PluginInstantiationException e1) {
							throw new RuntimeException (e1);
						}
				}

				final WorkspaceEntry openFile = workspace.getOpenFile(path);
				if (openFile != null && openFile.getObject() instanceof Model) {
					final Model model = (Model)openFile.getObject();
					JLabel label = new JLabel (model.getDisplayName()+ (model.getTitle().isEmpty()?"" : ("\"" + model.getTitle() + "\"" )));
					popup.add(label);
					popup.addSeparator();

					JMenuItem miOpenView = new JMenuItem("Open in editor");
					miOpenView.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							framework.getMainWindow().createEditorWindow(openFile);
						}
					});

					JMenuItem miSave = new JMenuItem("Save");
					miSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								framework.getMainWindow().save(openFile);
							} catch (OperationCancelledException e1) {
							}
						}
					});

					JMenuItem miSaveAs = new JMenuItem("Save as...");
					miSaveAs.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								framework.getMainWindow().saveAs(openFile);
							} catch (OperationCancelledException e1) {
							}
						}
					});

					popup.add(miSave);
					popup.add(miSaveAs);
					popup.add(miOpenView);

					ListMap<String, Pair<String, Tool>> applicableTools = Tools.getTools(model, framework);
					List<String> sections = Tools.getSections(applicableTools);

					if (!sections.isEmpty())
						popup.addSeparator();

					for (String section : sections) {
						JMenu m = new JMenu(section);

						for (Pair<String, Tool> tool : Tools.getSectionTools(section, applicableTools)) {
							JMenuItem mi = new JMenuItem(tool.getFirst());
							tools.put(mi, tool.getSecond());

							mi.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									Tools.run(model, tools.get(e.getSource()), framework);
								}
							});

							m.add(mi);
						}

						popup.add(m);
					}
				}

				popup.addSeparator();

				JMenuItem miRemove = new JMenuItem("Delete");
				miRemove.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try {
							workspace.delete(path);
						} catch (OperationCancelledException e1) { }
					}
				});
				popup.add(miRemove);
			}
			else
				for (Component c : wsWindow.createMenu().getMenuComponents())
					popup.add(c);
			return popup;
	}
}