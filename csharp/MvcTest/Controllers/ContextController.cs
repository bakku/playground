using Microsoft.AspNetCore.Mvc;
using MvcTest.ViewModels;

namespace MvcTest.Controllers;

public class ContextController : Controller
{
    [Route("/context")]
    public IActionResult Index()
    { 
        return View(new ContextIndex
        {
            Headers = HttpContext.Request.Headers
        });
    }
}