package org.nasdanika.workspace.wizard.render.app;

public class SessionInitializerRenderer {


  protected static String nl;
  public static synchronized SessionInitializerRenderer create(String lineSeparator)
  {
    nl = lineSeparator;
    SessionInitializerRenderer result = new SessionInitializerRenderer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.io.File;" + NL + "import java.util.ArrayList;" + NL + "import java.util.Collection;" + NL + "import java.util.List;" + NL + "" + NL + "import org.eclipse.emf.cdo.CDOObject;" + NL + "import org.eclipse.emf.cdo.common.id.CDOIDUtil;" + NL + "import org.eclipse.emf.cdo.common.model.CDOPackageRegistry;" + NL + "import org.eclipse.emf.cdo.eresource.CDOResource;" + NL + "import org.eclipse.emf.cdo.session.CDOSession;" + NL + "import org.eclipse.emf.cdo.transaction.CDOCommitContext;" + NL + "import org.eclipse.emf.cdo.transaction.CDOTransaction;" + NL + "import org.eclipse.emf.cdo.transaction.CDOTransactionHandler2;" + NL + "import org.eclipse.emf.cdo.util.CommitException;" + NL + "import org.eclipse.emf.common.util.URI;" + NL + "import org.eclipse.emf.ecore.EObject;" + NL + "import org.eclipse.emf.ecore.EReference;" + NL + "import org.eclipse.emf.ecore.resource.Resource;" + NL + "import org.eclipse.emf.ecore.resource.ResourceSet;" + NL + "import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;" + NL + "import org.eclipse.emf.ecore.util.EcoreUtil;" + NL + "import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;" + NL + "import org.nasdanika.cdo.CDOSessionInitializer;" + NL + "import org.nasdanika.core.CoreUtil;" + NL + "import org.nasdanika.core.CoreUtil.TokenSource;" + NL + "import org.osgi.service.component.ComponentContext;" + NL + "" + NL + "// Import model package(s)" + NL + "import ";
  protected final String TEXT_3 = ".";
  protected final String TEXT_4 = "Factory;" + NL + "import ";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = "Package;" + NL + "import ";
  protected final String TEXT_7 = ".";
  protected final String TEXT_8 = ";" + NL + "" + NL + "public class ";
  protected final String TEXT_9 = "SessionInitializerComponent implements CDOSessionInitializer {" + NL + "\t" + NL + "\tprivate List<String> initialContent = new ArrayList<>();" + NL + "\t" + NL + "\tpublic void activate(ComponentContext componentContext) {" + NL + "\t\tObject ic = componentContext.getProperties().get(\"initial-content\");" + NL + "\t\tTokenSource pts = token -> System.getProperties().get(token);" + NL + "\t\tif (ic instanceof String) {" + NL + "\t\t\tinitialContent.add(CoreUtil.interpolate((String) ic, pts));" + NL + "\t\t} else if (ic instanceof String[]) {" + NL + "\t\t\tfor (String ice: (String[]) ic) {" + NL + "\t\t\t\tinitialContent.add(CoreUtil.interpolate(ice, pts));" + NL + "\t\t\t}" + NL + "\t\t}\t\t" + NL + "\t}\t" + NL + "\t" + NL + "\t@Override" + NL + "\tpublic void init(CDOSession session) {" + NL + "\t\tSystem.out.println(\"Initializing session\");" + NL + "\t\t" + NL + "\t\t// Register packages" + NL + "\t\tCDOPackageRegistry packageRegistry = session.getPackageRegistry();" + NL + "\t\tpackageRegistry.putEPackage(";
  protected final String TEXT_10 = "Package.eINSTANCE);" + NL + "" + NL + "\t\tpackageRegistry.putEPackage(";
  protected final String TEXT_11 = "Package.eINSTANCE);" + NL + "\t\t" + NL + "\t\t// Populate with initial data if empty." + NL + "\t\tCDOTransaction transaction = session.openTransaction();\t\t\t\t" + NL + "\t\ttry {" + NL + "\t\t\tString resourceName = \"/";
  protected final String TEXT_12 = "\";" + NL + "\t\t\tif (!transaction.hasResource(resourceName) ) {" + NL + "\t\t\t\tCDOResource cRes = transaction.createResource(resourceName);" + NL + "\t\t\t\t" + NL + "\t\t\t\tResourceSet resourceSet = new ResourceSetImpl();" + NL + "\t\t\t\tresourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());" + NL + "" + NL + "\t\t\t\tresourceSet.getPackageRegistry().put(";
  protected final String TEXT_13 = "Package.eNS_URI, ";
  protected final String TEXT_14 = "Package.eINSTANCE);" + NL + "\t\t\t\t" + NL + "                List<Runnable> toRunOnCommitted = new ArrayList<>();" + NL + "" + NL + "                transaction.addTransactionHandler(new CDOTransactionHandler2() {" + NL + "" + NL + "                    @Override" + NL + "                    public void committedTransaction(CDOTransaction transaction, CDOCommitContext ctx) {" + NL + "                        for (Runnable r: toRunOnCommitted) {" + NL + "                            r.run();" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    @Override" + NL + "                    public void committingTransaction(CDOTransaction arg0, CDOCommitContext arg1) {" + NL + "                        // NOP" + NL + "                    }" + NL + "" + NL + "                    @Override" + NL + "                    public void rolledBackTransaction(CDOTransaction arg0) {" + NL + "                        // NOP" + NL + "                    }" + NL + "" + NL + "                });" + NL + "\t\t\t\t" + NL + "\t\t\t\tfor (String ic: initialContent) {" + NL + "\t\t\t\t\tFile file = new File(ic);" + NL + "\t\t\t\t\tif (file.isFile()) {\t\t\t\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\tURI uri = URI.createFileURI(file.getAbsolutePath());" + NL + "\t\t\t\t\t\tResource resource = resourceSet.getResource(uri, true);" + NL + "\t\t\t\t\t\tSystem.out.println(\"Loaded \" + uri);" + NL + "\t\t" + NL + "\t\t\t\t\t\t// Retrieve contents" + NL + "\t\t\t\t\t\tfor (EObject eObject : resource.getContents()) {" + NL + "\t\t\t\t\t\t\tcRes.getContents().add(EcoreUtil.copy(eObject));" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t" + NL + "                        toRunOnCommitted.add(new Runnable() {" + NL + "" + NL + "                            @Override" + NL + "                            public void run() {" + NL + "                                System.out.println(\"--- \"+file.getName()+\" ---\");" + NL + "                                for (EObject obj: cRes.getContents()) {" + NL + "                                    dump(obj, 4);" + NL + "                                }" + NL + "                            }" + NL + "                            " + NL + "                        });" + NL + "\t\t\t\t\t\t" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\tSystem.err.println(\"Initial content file does not exist or not a file: \"+file.getAbsolutePath());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\ttransaction.commit();" + NL + "\t\t\ttransaction.close();" + NL + "\t\t} catch (CommitException e) {" + NL + "\t\t\te.printStackTrace();" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "    @SuppressWarnings(\"unchecked\")" + NL + "    private void dump(EObject obj, int indent) {" + NL + "        if (obj != null) {" + NL + "            for (int i=0; i<indent; ++i) {" + NL + "                System.out.print(\" \");" + NL + "            }" + NL + "            if (obj instanceof CDOObject) {" + NL + "                StringBuilder sb = new StringBuilder();" + NL + "                CDOIDUtil.write(sb, ((CDOObject) obj).cdoID());" + NL + "                System.out.print(\"[\"+sb+\"] \");" + NL + "            }" + NL + "            System.out.print(obj.eClass().getName()+\" \");" + NL + "            System.out.println();" + NL + "            for (EReference ref: obj.eClass().getEAllReferences()) {" + NL + "                if (ref.isContainment()) {" + NL + "                    for (int i=0; i<indent+2; ++i) {" + NL + "                        System.out.print(\" \");" + NL + "                    }" + NL + "                    System.out.println(ref.getName());" + NL + "                    if (ref.isMany()) {" + NL + "                        for (Object rv: ((Collection<Object>) obj.eGet(ref))) {" + NL + "                            dump((EObject) rv, indent+4);" + NL + "                        }" + NL + "                    } else {" + NL + "                        dump((EObject) obj.eGet(ref), indent+4);" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "}" + NL;
  protected final String TEXT_15 = NL;

public String generate(org.nasdanika.workspace.wizard.WorkspaceWizard wizard) throws Exception
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(wizard.getApplicationArtifactId());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(wizard.getModelArtifactId());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(wizard.getModelArtifactId());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(wizard.getModelArtifactId());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(wizard.getJavaName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}