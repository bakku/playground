using Microsoft.AspNetCore.Mvc;
using MvcTest.Interfaces;

namespace MvcTest.Controllers;

[Route("/todos")]
public class TodosController(ITodoRepository todoRepository) : Controller
{
    [HttpGet("{id:guid}")]
    public IActionResult Show(Guid id)
    {
        var todo = todoRepository.FindOne(id);

        if (todo is null)
        {
            return NotFound();
        }
        
        return View(todo);
    }
}