using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using API.DTOs;
using API.Models;

namespace API.Services
{
    public interface IReservationService
    {
        Task<IAsyncResult> AddReservationAsync(ReservationToRecieveDTO reservation);
        Task<IAsyncResult> DeleteReservationAsync(Reservation reservation);
        Task<IAsyncResult> MarkReservationAsPaid(int resID);
        Task<IEnumerable<Reservation>> GetAllReservationsAsync();
        Task<IEnumerable<ReservationDTO>> GetMyReservationsAsync(int ID);
    }
}