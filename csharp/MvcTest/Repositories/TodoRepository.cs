using MvcTest.Interfaces;
using MvcTest.Models;

namespace MvcTest.Repositories;

public class TodoRepository : ITodoRepository
{
    private static readonly List<Todo> Todos =
    [
        new Todo
        {
            Id = Guid.NewGuid(),
            Title = "Buy Milk",
            Description = "We need more milk!"
        },
        new Todo
        {
            Id = Guid.NewGuid(),
            Title = "Improve Gazebo Structure",
            Description = "It might not be strong enough for the wind yet."
        }
    ];

    public List<Todo> FindAll() => Todos;

    public Todo? FindOne(Guid id) => Todos.FirstOrDefault(t => t.Id == id);
}