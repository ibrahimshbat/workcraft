package org.workcraft.plugins.cpog;

import java.util.Collection;
import java.util.LinkedList;

import org.workcraft.Config;
import org.workcraft.gui.DesktopApi;
import org.workcraft.gui.propertyeditor.PropertyDeclaration;
import org.workcraft.gui.propertyeditor.PropertyDescriptor;
import org.workcraft.gui.propertyeditor.Settings;

public class CpogSettings implements Settings {

	public enum SatSolver {
		MINISAT("MiniSat"),
		CLASP("Clasp");

		public final String name;

		private SatSolver(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final LinkedList<PropertyDescriptor> properties  = new LinkedList<PropertyDescriptor>();
	private static final String prefix = "CpogSettings";

	private static final String keySatSolver = prefix + ".satSolver";
	private static final String keyCircuitSize = prefix + ".circuitSize";
	private static final String keyClaspCommand = prefix + ".claspCommand";
	private static final String keyMinisatCommand = prefix + ".minisatCommand";
	private static final String keyScencoCommand = prefix + ".scencoCommand";
	private static final String keyEspressoCommand = prefix + ".espressoCommand";
	private static final String keyAbcFolder = prefix + ".abcFolder";
	private static final String keyGatesLibrary = prefix + ".gatesLibrary";
	private static final String keyPGMinerCommand = prefix + ".PGMinerCommand";
	private static final String keyUseSubscript = prefix + ".useSubscript";

	private static final SatSolver defaultSatSolver = SatSolver.CLASP;
	private static final int defaultCircuitSize = 4;
	private static final String defaultClaspCommand = "clasp";
	private static final String defaultMinisatCommand = "minisat";
	private static final String defaultScencoCommand = (DesktopApi.getOs().isWindows() ? "tools\\scenco\\scenco.exe" : "tools/scenco/scenco");
	private static final String defaultEspressoCommand = (DesktopApi.getOs().isWindows() ? "tools\\espresso\\espresso.exe" : "tools/espresso/espresso");
	private static final String defaultAbcFolder = "abc/";
	private static final String defaultGatesLibrary = "90nm.genlib";
	private static final String defaultPgminerCommand = (DesktopApi.getOs().isWindows() ? "tools\\pgminer\\pgminer.exe" : "tools/pgminer/pgminer");
	private static final boolean defaultUseSubscript = false;

	private static SatSolver satSolver = defaultSatSolver;
	private static int circuitSize = defaultCircuitSize;
	private static String claspCommand = defaultClaspCommand;
	private static String minisatCommand = defaultMinisatCommand;
	private static String scencoCommand = defaultScencoCommand;
	private static String espressoCommand = defaultEspressoCommand;
	private static String abcFolder = defaultAbcFolder;
	private static String gatesLibrary = defaultGatesLibrary;
	private static String pgminerCommand = defaultPgminerCommand;
	private static boolean useSubscript = defaultUseSubscript;

	public CpogSettings() {
		properties.add(new PropertyDeclaration<CpogSettings, SatSolver>(
				this, "SAT solver", SatSolver.class, true, false, false) {
			protected void setter(CpogSettings object, SatSolver value) {
				setSatSolver(value);
			}
			protected SatSolver getter(CpogSettings object) {
				return getSatSolver();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "Clasp solver command", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setClaspCommand(value);
			}
			protected String getter(CpogSettings object) {
				return getClaspCommand();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "MiniSat solver command", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setMinisatCommand(value);
			}
			protected String getter(CpogSettings object) {
				return getMinisatCommand();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "Scenco command", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setScencoCommand(value);
			}
			protected String getter(CpogSettings object) {
				return getScencoCommand();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "Espresso command", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setEspressoCommand(value);
			}
			protected String getter(CpogSettings object) {
				return getEspressoCommand();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "Abc folder path", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setAbcFolder(value);
			}
			protected String getter(CpogSettings object) {
				return getAbcFolder();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "Gate library (genlib format) inside abc folder", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setGatesLibrary(value);
			}
			protected String getter(CpogSettings object) {
				return getGatesLibrary();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, Boolean>(
				this, "\'_\' causes following text to be subscript in vertex and graph labels", Boolean.class, true, false, false) {
			protected void setter(CpogSettings object, Boolean value) {
				setUseSubscript(value);
			}
			protected Boolean getter(CpogSettings object) {
				return getUseSubscript();
			}
		});

		properties.add(new PropertyDeclaration<CpogSettings, String>(
				this, "PG miner command", String.class, true, false, false) {
			protected void setter(CpogSettings object, String value) {
				setPgminerCommand(value);
			}
			protected String getter(CpogSettings object) {
				return getPgminerCommand();
			}
		});
	}

	@Override
	public void load(Config config) {
		setSatSolver(config.getEnum(keySatSolver, SatSolver.class, defaultSatSolver));
		setCircuitSize(config.getInt(keyCircuitSize, defaultCircuitSize));
		setClaspCommand(config.getString(keyClaspCommand, defaultClaspCommand));
		setMinisatCommand(config.getString(keyMinisatCommand, defaultMinisatCommand));
		setScencoCommand(config.getString(keyScencoCommand, defaultScencoCommand));
		setEspressoCommand(config.getString(keyEspressoCommand, defaultEspressoCommand));
		setAbcFolder(config.getString(keyAbcFolder, defaultAbcFolder));
		setGatesLibrary(config.getString(keyGatesLibrary, defaultGatesLibrary));
		setPgminerCommand(config.getString(keyPGMinerCommand, defaultPgminerCommand));
		setUseSubscript(config.getBoolean(keyUseSubscript, defaultUseSubscript));
	}

	@Override
	public void save(Config config) {
		config.setEnum(keySatSolver, SatSolver.class, getSatSolver());
		config.setInt(keyCircuitSize, getCircuitSize());
		config.set(keyClaspCommand, getClaspCommand());
		config.set(keyMinisatCommand, getMinisatCommand());
		config.set(keyScencoCommand, getScencoCommand());
		config.set(keyEspressoCommand, getEspressoCommand());
		config.set(keyAbcFolder, getAbcFolder());
		config.set(keyGatesLibrary, getGatesLibrary());
		config.set(keyPGMinerCommand, getPgminerCommand());
		config.setBoolean(keyUseSubscript, getUseSubscript());
	}

	@Override
	public Collection<PropertyDescriptor> getDescriptors() {
		return properties;
	}

	@Override
	public String getSection() {
		return "External tools";
	}

	@Override
	public String getName() {
		return "SCENCO";
	}

	public static SatSolver getSatSolver() {
		return satSolver;
	}

	public static void setSatSolver(SatSolver value) {
		satSolver = value;
	}

	public static int getCircuitSize() {
		return circuitSize;
	}

	public static void setCircuitSize(int value) {
		circuitSize = value;
	}

	public static String getClaspCommand() {
		return claspCommand;
	}

	public static void setClaspCommand(String value) {
		claspCommand = value;
	}

	public static String getMinisatCommand() {
		return minisatCommand;
	}

	public static void setMinisatCommand(String value) {
		minisatCommand = value;
	}

	public static String getScencoCommand() {
		return scencoCommand;
	}

	public static void setScencoCommand(String value) {
		scencoCommand = value;
	}

	public static String getEspressoCommand() {
		return espressoCommand;
	}

	public static void setEspressoCommand(String value) {
		espressoCommand = value;
	}

	public static String getAbcFolder() {
		return abcFolder;
	}

	public static void setAbcFolder(String value) {
		abcFolder = value;
	}

	public static String getGatesLibrary() {
		return gatesLibrary;
	}

	public static void setGatesLibrary(String value) {
		gatesLibrary = value;
	}

	public static boolean getUseSubscript() {
		return useSubscript;
	}

	public static void setUseSubscript(boolean value) {
		useSubscript = value;
	}

	public static void setPgminerCommand(String value) {
		pgminerCommand = value;
	}

	public static String getPgminerCommand() {
		return pgminerCommand;
	}

}
