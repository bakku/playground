using MvcTest.Interfaces;
using MvcTest.Models;
using MvcTest.Repositories;

namespace MvcTest.Extensions;

public static class ServiceCollectionExtensions
{
    public static IServiceCollection AddPersistencyServices(this IServiceCollection services)
    {
        services.AddScoped<ITodoRepository, TodoRepository>();

        return services;
    }

    public static IServiceCollection AddConfigOptions(this IServiceCollection services, IConfiguration config)
    {
        services.AddOptions<SecretKeyOptions>()
            .Bind(config.GetSection("SecretKeys"))
            .ValidateDataAnnotations()
            .ValidateOnStart();

        return services;
    }
}