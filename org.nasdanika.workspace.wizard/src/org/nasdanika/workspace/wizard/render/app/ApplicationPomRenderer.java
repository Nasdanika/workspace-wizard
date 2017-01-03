package org.nasdanika.workspace.wizard.render.app;

public class ApplicationPomRenderer {


  protected static String nl;
  public static synchronized ApplicationPomRenderer create(String lineSeparator)
  {
    nl = lineSeparator;
    ApplicationPomRenderer result = new ApplicationPomRenderer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">" + NL + "  <modelVersion>4.0.0</modelVersion>" + NL + "  <artifactId>";
  protected final String TEXT_2 = "</artifactId>" + NL + "  <packaging>eclipse-plugin</packaging>" + NL + "  <name>";
  protected final String TEXT_3 = " Application</name>" + NL + "  <parent>" + NL + "  \t<groupId>";
  protected final String TEXT_4 = "</groupId>" + NL + "  \t<artifactId>";
  protected final String TEXT_5 = ".parent</artifactId>" + NL + "  \t<version>";
  protected final String TEXT_6 = "-SNAPSHOT</version>" + NL + "  \t<relativePath>../";
  protected final String TEXT_7 = ".parent</relativePath>" + NL + "  </parent>" + NL + "</project>";

public String generate(org.nasdanika.workspace.wizard.WorkspaceWizard wizard) throws Exception
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(wizard.getApplicationArtifactId());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(wizard.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(wizard.getGroupId());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(wizard.getGroupId());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(wizard.getVersion());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(wizard.getGroupId());
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}