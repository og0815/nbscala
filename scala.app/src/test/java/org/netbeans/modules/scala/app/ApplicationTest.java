package org.netbeans.modules.scala.app;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.api.scala.platform.ScalaPlatform;
import org.netbeans.api.scala.platform.ScalaPlatformManager;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.NewProjectWizardOperator;
import org.netbeans.jellytools.ProjectsTabOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.ActionNoBlock;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jellytools.nodes.ProjectRootNode;
import org.netbeans.jellytools.properties.editors.FileCustomEditorOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextPaneOperator;
import org.netbeans.junit.NbModuleSuite;

public class ApplicationTest extends JellyTestCase {

    private final String SAMPLE_SCALA_MAVEN = System.getProperty("sample-scala-maven");
    private final String SAMPLE_SCALA_JAVA_MAVEN = System.getProperty("sample-scala-java-maven");

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(true).
                clusters(".*").
                failOnMessage(Level.SEVERE).
// TODO: Can be enables if the java.lang.ClassCastException: org.netbeans.api.project.ProjectUtils$AnnotateIconProxyProjectInformation
// cannot be cast to org.netbeans.modules.scala.project.J2SEProject$Info is solved
//                failOnException(Level.INFO).
                suite();
    }

    public ApplicationTest(String n) {
        super(n);
    }

    /**
     * Test if a ScalaPlatform is found.
     */
    public void testPlatformIsFound() {
        String scalaHome = System.getenv("SCALA_HOME");
        if (scalaHome == null || scalaHome.trim().equals("")) return; // No Scala Home is set, Shell will not work.
        File scalaHomeDir = new File(scalaHome);
        assertTrue("Environment Variable SCALA_HOME=" + scalaHome + " doesn't point to an existing directory",scalaHomeDir.exists());
        assertTrue("Environment Variable SCALA_HOME=" + scalaHome + " doesn't point to a directory",scalaHomeDir.isDirectory());
        // TODO: Find a way to test the Application without the Platform istalled.
        // e.g., Put platform in Testdirectory. Set System Environment Variable. Start.        
        ScalaPlatform platform = ScalaPlatformManager.getDefault().getDefaultPlatform();
        // TODO: See what Linux is doing.
        System.out.println(platform.getClass());
        System.out.println("BootstrapLibraries:" + platform.getBootstrapLibraries());
        System.out.println("DisplayName:" + platform.getDisplayName());
        System.out.println("InstallFolders:" + platform.getInstallFolders());
        System.out.println("Javadoc:" + platform.getJavadocFolders());
        System.out.println("Properties:" + platform.getProperties());
        System.out.println("SourceFolders:" + platform.getSourceFolders());
        System.out.println("Specification:" + platform.getSpecification());
        System.out.println("StandardLibraries:" + platform.getStandardLibraries());
        System.out.println("SystemProperties:" + platform.getSystemProperties());
        System.out.println("Vendor:" + platform.getVendor());        
        
/*
class: class org.netbeans.modules.scala.stdplatform.platformdefinition.DefaultPlatformImpl
BootstrapLibraries:C:\Program Files\Java\jdk1.6.0_27\jre\lib\resources.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\rt.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\jce.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\modules\jdk.boot.jar;C:\Program Files\Java\jdk1.6.0_27\jre\classes;C:\Program Files\Java\jdk1.6.0_27\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.6.0_27\jre\lib\ext\sunjce_provider.jar;C:\scala-2.9.1\lib\scala-library.jar
DisplayName:Scala 1.1 (Default)
InstallFolders:[C:\scala-2.9.1@dd2a1905:e068ad3]
Javadoc:[file:/C:/scala-2.9.1/doc/]
Properties:{scala.class.path=C:\scala-2.9.1\lib\jline.jar;C:\scala-2.9.1\lib\scala-compiler.jar;C:\scala-2.9.1\lib\scala-dbc.jar;C:\scala-2.9.1\lib\scala-library.jar;C:\scala-2.9.1\lib\scala-partest.jar;C:\scala-2.9.1\lib\scala-swing.jar;C:\scala-2.9.1\lib\scalacheck.jar;C:\scala-2.9.1\lib\scalap.jar, scala.platform.ant.name=default_platform}
SourceFolders:C:\scala-2.9.1\src\scala-compiler-src.jar;C:\scala-2.9.1\src\scala-dbc-src.jar;C:\scala-2.9.1\src\scala-library-src.jar;C:\scala-2.9.1\src\scala-partest-src.jar;C:\scala-2.9.1\src\scala-swing-src.jar;C:\scala-2.9.1\src\scalap-src.jar
Specification:std 1.1 
StandardLibraries:C:\scala-2.9.1\lib\jline.jar;C:\scala-2.9.1\lib\scala-compiler.jar;C:\scala-2.9.1\lib\scala-dbc.jar;C:\scala-2.9.1\lib\scala-library.jar;C:\scala-2.9.1\lib\scala-partest.jar;C:\scala-2.9.1\lib\scala-swing.jar;C:\scala-2.9.1\lib\scalacheck.jar;C:\scala-2.9.1\lib\scalap.jar
SystemProperties:{java.util.logging.config.class=org.netbeans.core.startup.TopLogging, java.vm.version=20.2-b06, java.vendor.url=http://java.sun.com/, sun.jnu.encoding=Cp1252, test=org.netbeans.modules.scala.app.ApplicationTest, java.vm.info=mixed mode, user.dir=C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app, sun.cpu.isalist=amd64, java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment, sun.os.patch.level=Service Pack 1, netbeans.security.nocheck=true, scala.home=C:\scala-2.9.1, org.openide.version=deprecated, netbeans.home=C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\nbscala\platform, java.io.tmpdir=C:\Temp\, user.home=C:\Users\oliver.guenther, java.awt.printerjob=sun.awt.windows.WPrinterJob, java.version=1.6.0_27, nb.show.statistics.ui=usageStatisticsEnabled, file.encoding.pkg=sun.io, netbeans.logger.console=true, java.vendor.url.bug=http://java.sun.com/cgi-bin/bugreport.cgi, file.encoding=Cp1252, line.separator=
, sun.java.command=C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\surefire\surefirebooter7404915480587338145.jar C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\surefire\surefire5907019937480785449tmp C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\surefire\surefire197113399144075613tmp, 
branding.token=nbscala, netbeans.full.hack=true, java.vm.specification.vendor=Sun Microsystems Inc., java.vm.vendor=Sun Microsystems Inc., 
java.class.path=C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\test-classes;C:\Users\oliver.guenther\Development\nbscala-og0815\scala.app\target\classes;C:\Users\oliver.guenther\.m2\repository\org\netbeans\modules\org-netbeans-libs-scala-continuations\2.9.2\org-netbeans-libs-scala-continuations-2.9.2.jar;C:\Users\oliver.guenther\.m2\repository\org\scala-lang\plugins\continuations\2.9.2.nbscala\continuations-2.9.2.nbscala.jar;C:\Users\oliver.guenther\.m2\repository\org\netbeans\modules\org-netbeans-libs-scala-compiler\2.9.2\org-netbeans-libs-scala-compiler-2.9.2.jar;C:\Users\oliver.guenther\.m2\repository\org\scala-lang\scala-compiler\2.9.2.nbscala\scala-compiler-2.9.2.nbscala.jar;C:\Users\oliver.guenther\.m2\repository\org\netbeans\modules\org-netbeans-libs-scala-library\2.9.2\org-netbeans-libs-scala-library-2.9.2.jar;C:\Users\oliver.guenther\.m2\repository\org\scala-lang\scala-library\2.9.2.nbscala\scala-library-2.9.2.nbscala.jar;C:\Users\oliver.guenther\.m2\repository\org\netbeans\modules\org-netbeans-libs-xtc\1.15\org-netbeans-libs-xtc-1.15.jar;C:\Users\oliver.guenther\.m2\repository\org\netbeans\modules\org-netbeans-modules-scala-console\0.11\org-netbeans-modules-scala-console-0.11.jar;C:\Users\oliver.guenther\.m2\repository\org\netbeans\api\org-netbeans-modules-extexecution\RELEASE72\org-netbeans-modules-extexecution-RELEASE72.jar;
...
C:\Users\oliver.guenther\.m2\repository\org\hamcrest\hamcrest-core\1.1\hamcrest-core-1.1.jar;C:\Program Files\Java\jdk1.6.0_27\jre\..\lib\tools.jar;}
Vendor:Sun Microsystems Inc.
*/
    }

    public void ignoreTestApplication() {
        new ActionNoBlock("Help|About", null).performMenu();
        new NbDialogOperator("About").closeByButton();
    }

    /**
     * Test if the ScalaShell is enabled and opens.
     */
    public void ignoreTestInteractiveScalaShell() {
        String scalaHome = System.getenv("SCALA_HOME");
        if (scalaHome == null || scalaHome.trim().equals("")) return; // No Scala Home is set, Shell will not work.
        File scalaHomeDir = new File(scalaHome);
        assertTrue("Environment Variable SCALA_HOME=" + scalaHome + " doesn't point to an existing directory",scalaHomeDir.exists());
        assertTrue("Environment Variable SCALA_HOME=" + scalaHome + " doesn't point to a directory",scalaHomeDir.isDirectory());
        ActionNoBlock openScalaShell = new ActionNoBlock("Window|Other|Interactive Scala Shell", null);
        // TODO: If the Action Interactive Sacla Shell is missing the test will block here. Some check before this point, if it exist, would be nice.
        openScalaShell.performMenu();
        TopComponentOperator scalaConsole = new TopComponentOperator("Scala Console");
        assertTrue("Scala Console not showing",scalaConsole.isShowing());
        // TODO: Same as before, a test for existence would be nice.
        JTextPaneOperator theConsole = new JTextPaneOperator(scalaConsole);
        assertTrue("Scala Console is not Enabled", theConsole.isEnabled());
        theConsole.enterText(":quit");
        theConsole.waitComponentShowing(false);
        assertFalse("Scala Console still visible, should be closed",theConsole.isShowing());
    }

    public void ignoreTestNewScalaProject() {
        NewProjectWizardOperator newProjectWizard = NewProjectWizardOperator.invoke();
        newProjectWizard.selectCategory("Scala");
        newProjectWizard.selectProject("Scala Application");
        newProjectWizard.next();
        newProjectWizard.finish();

        // TODO: Cause of the missing JUnit dependencies, a resolve references Dialog is opening.
        // IDEA: Change the default Project to have no dependency to JUnit
        new NbDialogOperator("Open Project").btClose();

        // TODO: Some asserts or an actuall compile to make sure the project is active and alive.
    }

    /**
     * A Test to see if a scala maven project is discovert and the scala sources
     * are displayed in the source node.
     */
    // TODO: Can only be enabled if http://netbeans.org/bugzilla/show_bug.cgi?id=216738 is solved.
    public void ignoreTestScalaMavenProject() {
        new ActionNoBlock("File|Import|From ZIP", null).performMenu();
        new NbDialogOperator("Import Project(s) from ZIP").pushKey(KeyEvent.VK_TAB);
        new NbDialogOperator("Import Project(s) from ZIP").pushKey(KeyEvent.VK_SPACE);
        new FileCustomEditorOperator("Öffnen").setSelectedFile(SAMPLE_SCALA_MAVEN);
        new FileCustomEditorOperator("Öffnen").pushKey(KeyEvent.VK_ENTER);
        new JButtonOperator(new NbDialogOperator("Import Project(s) from ZIP"), "Import").push();
        ProjectsTabOperator pto = ProjectsTabOperator.invoke();
        ProjectRootNode prn = pto.getProjectRootNode("sample-scala-maven");
        Node node = new Node(prn, "Scala Packages|sample|App.scala");
        node.select();
        node.performPopupAction("Open");
        new ActionNoBlock("Window|Action Items", null).performMenu();
        TopComponentOperator actionItems = new TopComponentOperator("Action Items");
        actionItems.makeComponentVisible();
        JTableOperator t = new JTableOperator(actionItems);
        int row = t.findCellColumn("SampleJavaError");
        assertTrue("No Error SampleJavaError in the Action Itmes found, but should be", row >= 0);
        row = t.findCellColumn("Blub");
        assertTrue("Error Blub in the Action Itmes found, but should not be", row == -1);
    }

    /**
     * A Test to see if a combinded scala java maven project is discovert and contains no errors.
     * At the moment this is the case.
     */
    // TODO: Can only be enabled if http://netbeans.org/bugzilla/show_bug.cgi?id=216738 is solved.
    public void ignoreTestScalaJavaMavenProject() {
        new ActionNoBlock("File|Import|From ZIP", null).performMenu();
        new NbDialogOperator("Import Project(s) from ZIP").pushKey(KeyEvent.VK_TAB);
        new NbDialogOperator("Import Project(s) from ZIP").pushKey(KeyEvent.VK_SPACE);
        new FileCustomEditorOperator("Öffnen").setSelectedFile(SAMPLE_SCALA_JAVA_MAVEN);
        new FileCustomEditorOperator("Öffnen").pushKey(KeyEvent.VK_ENTER);
        new JButtonOperator(new NbDialogOperator("Import Project(s) from ZIP"), "Import").push();
        ProjectsTabOperator pto = ProjectsTabOperator.invoke();
        ProjectRootNode prn = pto.getProjectRootNode("sample-scala-java-maven");
        prn.select();
        Node node = new Node(prn, "Source Packages|sample|Runner.java");
        node.select();
        node.performPopupAction("Open");
        // Opening the Action Items to see if the selecte Java Class has an Error.
        new ActionNoBlock("Window|Action Items", null).performMenu();
        TopComponentOperator actionItems = new TopComponentOperator("Action Items");
        actionItems.makeComponentVisible();
        JTableOperator t = new JTableOperator(actionItems);
        int row = t.findCellColumn("Runner.java");
        // The following Assert should validate to -1, meaning there is no row containing the String Runner.java
        // At the moment this is not the case. A mixed scala-java project works, but displays erros in the ui.
        // assertTrue("No Error Runner.java in the Action Itmes found, but should be", row == -1);
    }

}
