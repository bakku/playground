using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;
using MvcTest.Models;
using MvcTest.ViewModels;

namespace MvcTest.Controllers;

[Route("/configuration")]
public class ConfigurationController(ILogger<ConfigurationController> logger, IConfiguration configuration, IOptions<SecretKeyOptions> secretKeyOptionsDi)
    : Controller
{
    public IActionResult Index()
    {
        // Manually
        var secretKeyOptionsManually = new SecretKeyOptions();
        configuration.GetSection("SecretKeys").Bind(secretKeyOptionsManually);

        return View(new ConfigurationIndex
        {
            SecretKeyOptionsManually = secretKeyOptionsManually,
            SecretKeyOptionsDi = secretKeyOptionsDi
        });
    }
}