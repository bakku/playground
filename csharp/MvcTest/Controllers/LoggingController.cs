using Microsoft.AspNetCore.Mvc;

namespace MvcTest.Controllers;

[Route("/logging")]
public class LoggingController(ILogger<LoggingController> logger) : Controller
{
    public IActionResult Index()
    {
        logger.LogInformation("Received request to Logging#Index");
        
        return RedirectToAction(actionName: "Index", controllerName: "Home");
    }
}