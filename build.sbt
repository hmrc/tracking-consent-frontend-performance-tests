lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .enablePlugins(CorePlugin)
  .enablePlugins(JvmPlugin)
  .enablePlugins(IvyPlugin)
  .settings(
    name := "tracking-consent-frontend-performance-tests",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.12",
    libraryDependencies ++= Seq(
      Dependencies.Compile.typesafeConfig,
      Dependencies.Compile.gatlingHighCharts,
      Dependencies.Compile.gatlingTestFramework,
      Dependencies.Compile.performanceTestRunner
    ),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-language:_",
      "-target:jvm-1.8",
      "-Xmax-classfile-name", "100",
      "-encoding", "UTF-8"
    ),
    javaOptions in Gatling ++= overrideDefaultJavaOptions("-Xms4096m", "-Xmx16384m"),
    retrieveManaged := true,
    initialCommands in console := "import uk.gov.hmrc._",
    parallelExecution in Test := false,
    publishArtifact in Test := true,
    resolvers ++= Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )
