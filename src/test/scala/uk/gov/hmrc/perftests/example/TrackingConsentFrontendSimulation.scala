/*
 * Copyright 2020 HM Revenue & Customs
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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.TrackingConsentFrontendRequests._

class TrackingConsentFrontendSimulation extends PerformanceTestRunner {

  setup("download", "Retrieve the tracking script") withRequests requestTrackingJs
  setup("audit", "Audit the tracking consent decision") withRequests postToAuditEndpoint
  setup("cookie-settings", "Visit the cookie settings page") withRequests navigateToCookieSettings

  runSimulation()
}
