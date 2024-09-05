using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using MvcTest.Interfaces;
using MvcTest.Models;

namespace MvcTest.Controllers;

[Route("/")]
public class HomeController(ILogger<HomeController> logger, ITodoRepository todoRepository, IConfiguration configuration)
    : Controller
{
    [HttpGet]
    public IActionResult Index()
    {
        return View(todoRepository.FindAll());
    }

    [HttpGet("privacy")]
    public IActionResult Privacy()
    {
        return View();
    }

    [HttpGet("error")]
    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
