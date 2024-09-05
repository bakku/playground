using System.ComponentModel.DataAnnotations;

namespace MvcTest.Models;

public class SecretKeyOptions
{
    public string GoogleApiKey { get; set; } = string.Empty;
    
    public string PaypalSellerKey { get; set; } = string.Empty;
    
    [Range(0, 10, ErrorMessage = "Value for {0} must be between {1} and {2}")]
    public int SecretRandomNumber { get; set; } = default;

    public override string ToString()
    {
        return $"GoogleApiKey: {GoogleApiKey}, " +
               $"PaypalSellerKey: {PaypalSellerKey}, " +
               $"SecretRandomNumber: {SecretRandomNumber}";
    }
}