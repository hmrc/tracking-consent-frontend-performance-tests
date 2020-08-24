lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .enablePlugins(SbtAutoBuildPlugin)
  .settings(
    name := "tracking-consent-frontend-performance-tests",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.12",
    libraryDependencies ++= Dependencies.test,
    //implicitConversions & postfixOps are Gatling recommended -language settings
    scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps"),
    javaOptions in Gatling ++= overrideDefaultJavaOptions("-Xms4096m", "-Xmx16384m"),
    // Enabling sbt-auto-build plugin provides DefaultBuildSettings with default `testOptions` from `sbt-settings` plugin.
    // These testOptions are not compatible with `sbt gatling:test`. So we have to override testOptions here.
    testOptions in Test := Seq.empty,
    resolvers ++= Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    )
  )
