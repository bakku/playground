using Microsoft.Extensions.Diagnostics.HealthChecks;

namespace MvcTest.HealthChecks;

public class InternetAvailableHealthCheck(ILogger<InternetAvailableHealthCheck> logger, IConfiguration config) : IHealthCheck
{
    public async Task<HealthCheckResult> CheckHealthAsync(HealthCheckContext context, CancellationToken cancellationToken = default)
    {
        var requestUri = config["HealthChecks:InternetAvailable:Url"];
        
        logger.LogInformation("Running internet available health check against {requestUri}", requestUri);
        
        using var client = new HttpClient();
        
        var response = await client.GetAsync(requestUri, cancellationToken);

        return !response.IsSuccessStatusCode 
            ? HealthCheckResult.Unhealthy($"internet available health check: did not receive response from {requestUri}")
            : HealthCheckResult.Healthy($"internet available health check: successful response from {requestUri}");
    }
}