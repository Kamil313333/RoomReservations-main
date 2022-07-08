using System.Threading.Tasks;
using API.DTOs;

namespace API.Services
{
    public interface IPaymentService
    {
        Task<PaymentResponse> Charge(PaymentDTO payment);
    }
}