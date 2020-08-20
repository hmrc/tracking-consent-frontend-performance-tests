lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .enablePlugins(CorePlugin)
  .enablePlugins(JvmPlugin)
  .enablePlugins(IvyPlugin)
  .enablePlugins(SbtAutoBuildPlugin)
  .settings(
    name := "tracking-consent-frontend-performance-tests",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.12",
    libraryDependencies ++= Dependencies.test,
    scalacOptions ++= Seq("-feature"),
    javaOptions in Gatling ++= overrideDefaultJavaOptions("-Xms4096m", "-Xmx16384m"),
    retrieveManaged := true,
    initialCommands in console := "import uk.gov.hmrc._",
    parallelExecution in Test := false,
    publishArtifact in Test := true,
    // Enabling sbt-auto-build plugin provides DefaultBuildSettings with default `testOptions` from `sbt-settings` plugin.
    // These testOptions are not compatible with `sbt gatling:test`. So we have to override testOptions here.
    testOptions in Test := Seq.empty,
    resolvers ++= Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )
