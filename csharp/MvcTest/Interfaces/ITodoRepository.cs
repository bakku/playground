using MvcTest.Models;

namespace MvcTest.Interfaces;

public interface ITodoRepository
{
    List<Todo> FindAll();

    Todo? FindOne(Guid id);
}