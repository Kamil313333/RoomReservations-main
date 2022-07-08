using System.Security.Claims;
using System.Threading.Tasks;
using API.DTOs;
using API.Models;
using API.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    // [Authorize]
    [ApiController]
    [Route("api/reservation/")]
    public class ReservationController : ControllerBase
    {
        private readonly IReservationService reservationService;
        private readonly IPaymentService paymentService;


        public ReservationController(IReservationService reservationService, IPaymentService paymentService)
        {
            this.reservationService = reservationService;
            this.paymentService = paymentService;
        }

        [HttpPost("add")]
        public async Task<IActionResult> AddReservationAsync([FromBody] ReservationToRecieveDTO reservation)
        {
            return Ok(await reservationService.AddReservationAsync(reservation));
        }

        [HttpGet("getAll")]
        public async Task<IActionResult> GetAllReservationsAsync()
        {
            return Ok(await reservationService.GetAllReservationsAsync());
        }

        [HttpGet("getMy")]
        public async Task<IActionResult> GetMyReservationsAsync()
        {
            var userID = int.Parse(User.FindFirst(ClaimTypes.NameIdentifier).Value);
            return Ok(await reservationService.GetMyReservationsAsync(userID));
        }

        [HttpDelete("delete")]
        public async Task<IActionResult> DeleteReservationAsync([FromBody] Reservation reservation)
        {
            return Ok(await reservationService.DeleteReservationAsync(reservation));
        }

        [HttpPost("payment")]
        public async Task<IActionResult> PaymentForReservation([FromBody] PaymentDTO payment)
        {
            return Ok(await paymentService.Charge(payment));
        }

        [HttpPost("mark-paid/{resID}")]
        public async Task<IActionResult> MarkAsPaid(int resID)
        {
            return Ok(await reservationService.MarkReservationAsPaid(resID));
        }
    }
}