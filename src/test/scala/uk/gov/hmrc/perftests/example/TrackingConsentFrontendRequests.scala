/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.conf.ServicesConfiguration

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TrackingConsentFrontendRequests extends ServicesConfiguration {

  val baseUrl = baseUrlFor("tracking-consent-frontend")

  val requestTrackingJs = {
    http("Navigate to tracking.js")
      .get(s"$baseUrl/tracking-consent/tracking.js")
      .check(status.is(200))
  }

  val navigateToCookieSettings = {
    http("Navigate to cookie settings")
      .get(s"$baseUrl/tracking-consent/cookie-settings?enableTrackingConsent=true")
      .check(status.is(200))
  }

  val postToAuditEndpoint = {
    http("Audit the tracking consent decision")
      .post(s"$baseUrl/tracking-consent/audit")
      .header(HttpHeaderNames.ContentType, "application/json")
      .body(StringBody("{\"measurement\":true,\"marketing\":true,\"settings\":true}"))
      .check(status.is(200))
  }
}
