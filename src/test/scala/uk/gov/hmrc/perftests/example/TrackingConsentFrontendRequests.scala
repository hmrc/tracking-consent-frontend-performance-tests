/*
 * Copyright 2024 HM Revenue & Customs
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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import scala.io.Source
import scala.util.matching.Regex

object TrackingConsentFrontendRequests extends ServicesConfiguration {

  private val baseUrl = baseUrlFor("tracking-consent-frontend")
  private val defaultHmrcFrontendVersion = "5.66.0"

  val getTrackingJs: HttpRequestBuilder = {
    http("GET tracking.js")
      .get(s"$baseUrl/tracking-consent/tracking.js")
      .check(status.is(200))
  }

  val getOptimizelyJs: HttpRequestBuilder = {
    http("GET optimizely.js")
      .get(s"$baseUrl/tracking-consent/tracking/optimizely.js")
      .check(status.is(200))
  }

  val getCookieSettingsPage: HttpRequestBuilder = {
    val versionRegex: Regex = raw"hmrc-frontend-([0-9]+\.[0-9]+\.[0-9]+)\.min.js".r
    val content: String = Source.fromURL(s"$baseUrl/tracking-consent/cookie-settings").mkString
    val hmrcFrontendVersion = versionRegex.findFirstMatchIn(content) match {
      case Some(matched) =>
        matched.group(1)
      case None =>
        defaultHmrcFrontendVersion
    }
    http("Load cookie settings page, with assets")
      .get(s"$baseUrl/tracking-consent/cookie-settings?enableTrackingConsent=true")
      .resources(
        http("hmrc-frontend-css").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/hmrc-frontend-$hmrcFrontendVersion.min.css"),
        http("hmrc-frontend-js").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/hmrc-frontend-$hmrcFrontendVersion.min.js"),
        http("application-css").get(s"$baseUrl/tracking-consent/assets/stylesheets/application.css"),
        http("settings-js").get(s"$baseUrl/tracking-consent/assets/settingsPage.js"),
        http("optimizely-js").get(s"$baseUrl/tracking-consent/assets/optimizely.js"),
        http("hmrc-frontend-govuk-crest").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/govuk/images/govuk-crest-2x.png"),
        http("hmrc-frontend-font-bold").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/govuk/fonts/bold-b542beb274-v2.woff2"),
        http("hmrc-frontend-font-light").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/govuk/fonts/light-94a07e06a1-v2.woff2"),
        http("hmrc-frontend-favicon").get(s"$baseUrl/tracking-consent/hmrc-frontend/assets/govuk/images/favicon.ico")
      )
      .check(status.is(200))
  }

  val postToAuditEndpoint: HttpRequestBuilder = {
    http("Audit the tracking consent decision")
      .post(s"$baseUrl/tracking-consent/audit")
      .header(HttpHeaderNames.ContentType, "application/json")
      .body(StringBody("{\"measurement\":true,\"marketing\":true,\"settings\":true}"))
      .check(status.is(200))
  }
}
