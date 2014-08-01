package org.workcraft.plugins.dfs.tools;


import org.workcraft.Framework;
import org.workcraft.Tool;
import org.workcraft.plugins.dfs.Dfs;
import org.workcraft.plugins.dfs.tasks.CheckDataflowDeadlockTask;
import org.workcraft.plugins.mpsat.MpsatChainResultHandler;
import org.workcraft.workspace.WorkspaceEntry;

public class CheckDataflowDeadlockTool implements Tool {
	private final Framework framework;

	public CheckDataflowDeadlockTool(Framework framework) {
		this.framework = framework;
	}

	public String getDisplayName() {
		return "Check dataflow for deadlocks";
	}

	@Override
	public String getSection() {
		return "Verification";
	}

	@Override
	public boolean isApplicableTo(WorkspaceEntry we) {
		return we.getModelEntry().getMathModel() instanceof Dfs;
	}

	@Override
	public void run(WorkspaceEntry we) {
		final CheckDataflowDeadlockTask task = new CheckDataflowDeadlockTask(we, framework);
		String description = "MPSat tool chain";
		String title = we.getModelEntry().getModel().getTitle();
		if (!title.isEmpty()) {
			description += "(" + title +")";
		}
		framework.getTaskManager().queue(task, description, new MpsatChainResultHandler(task));
	}

}