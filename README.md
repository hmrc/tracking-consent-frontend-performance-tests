# tracking-consent-frontend-performance-tests

Performance test repository for tracking-consent-frontend, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood

The `TrackingConsentFrontendRequests` tests will automatically try to find the version of hmrc-frontend in staging. To do this, it looks at https://www.staging.tax.service.gov.uk/help/cookie-details and scrapes the version of `hmrc-frontend-{version}.min.js`. Should staging go down or you're unable to reach this, it will default to the defaultHmrcFrontendVersion.

```
  private val defaultHmrcFrontendVersion = "5.66.0"
```

### Smoke test

It might be useful to try the journey with one user to check that everything works fine before running the full performance test
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

### Run the performance test
```
sbt gatling:test
```
