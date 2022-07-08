using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using API.DTOs;
using Stripe;

namespace API.Services
{
    public class PaymentService : IPaymentService
    {
        public PaymentService()
        {
            
        }

        public async Task<PaymentResponse> Charge(PaymentDTO payment)
        {
            Stripe.StripeConfiguration.ApiKey = "sk_test_51LCPghFrUnnbVHsv30hkbJcO7xp9dvKpGtVDIlM8PuIAbnj0azZFhgxqznFGU4UvddDR8D7tH8ZSRr1NKja3ynRe00J9fzDsRg";

            var options = new PaymentIntentCreateOptions
            {
                Amount = payment.Amount * 100,
                Currency = "PLN",
                PaymentMethodTypes = new List<string> { "card"},
            };

            var service = new PaymentIntentService();
            var result = await service.CreateAsync(options);

            return new PaymentResponse { ClientSecret = result.ClientSecret };
        }
    }
}