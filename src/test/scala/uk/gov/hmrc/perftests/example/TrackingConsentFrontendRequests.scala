package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.conf.ServicesConfiguration

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TrackingConsentFrontendRequests extends ServicesConfiguration {

  val baseUrl = baseUrlFor("tracking-consent")

  val navigateToTrackingJs = {
    http("Navigate to tracking.js")
      .get(s"$baseUrl/tracking-consent/tracking.js")
      .check(status.is(200))
  }
}
