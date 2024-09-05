using Microsoft.Extensions.Options;
using MvcTest.Models;

namespace MvcTest.ViewModels;

public class ConfigurationIndex
{
    public required SecretKeyOptions SecretKeyOptionsManually { get; init; }
    public required IOptions<SecretKeyOptions> SecretKeyOptionsDi { get; init; }
}