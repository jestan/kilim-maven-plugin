package io.kilimty;

import kilim.tools.Weaver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Kilim class file weaver..!
 */
@Mojo(name = "weave", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class KilimWeaverMojo extends AbstractMojo {

    /**
     * Location of the class files.
     */
    @Parameter(defaultValue = "${project.build.directory}", property = "inputDir", required = true)
    private File inputDirectory;


    /**
     * Location of the weaved class files.
     */
    @Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
    private File outputDirectory;


    public void execute() throws MojoExecutionException {
        try {
            Weaver.outputDir = outputDirectory.getAbsolutePath();
            Weaver.main(new String[]{inputDirectory.getAbsolutePath()});
        } catch (Exception e) {
            throw new MojoExecutionException("Error while weaving the classes", e);
        }
    }
}
