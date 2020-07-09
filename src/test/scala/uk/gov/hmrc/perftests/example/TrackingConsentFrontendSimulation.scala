package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.TrackingConsentFrontendRequests._

class TrackingConsentFrontendSimulation extends PerformanceTestRunner {

  setup("download", "Retrieve the tracking script") withRequests navigateToTrackingJs
  setup("cookie-settings", "Visit the cookie settings page") withRequests navigateToCookieSettings

  runSimulation()
}
