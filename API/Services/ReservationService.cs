using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Database;
using API.DTOs;
using API.Models;
using Microsoft.EntityFrameworkCore;

namespace API.Services
{
    public class ReservationService : IReservationService
    {
        private readonly DataContext context;
        public ReservationService(DataContext context)
        {
            this.context = context;
        }
        public async Task<IAsyncResult> AddReservationAsync(ReservationToRecieveDTO reservation)
        {
            var res = new Reservation()
            {
                RoomId = reservation.RoomID,
                UserID = reservation.UserID,
                GuestsCount = reservation.GuestsCount,
                DateFrom = reservation.DateFrom,
                DateTo = reservation.DateTo
            };
            await context.AddAsync(res);
            await context.SaveChangesAsync();
            return Task.CompletedTask;
        }

        public async Task<IAsyncResult> DeleteReservationAsync(Reservation reservation)
        {
            context.Reservations.Remove(reservation);
            await context.SaveChangesAsync();
            return Task.CompletedTask;
        }

        public async Task<IEnumerable<Reservation>> GetAllReservationsAsync()
        {
            return await context.Reservations.ToListAsync();
        }

        public async Task<IEnumerable<ReservationDTO>> GetMyReservationsAsync(int ID)
        {
            return await context.Reservations
                .Include(x => x.Room)
                .Where(x => x.UserID == ID)
                .Select(x => new ReservationDTO 
                {
                    DateFrom = x.DateFrom,
                    DateTo = x.DateTo,
                    DaysToStay = 2,
                    DoorNum = x.Room.DoorNum,
                    GuestCount = x.GuestsCount,
                    ImageURL = x.Room.ImageURL,
                    ReservationID = x.ID,
                    HasPaid = x.HasPaid,
                    PricePerDay = x.Room.PricePerDay
                })
                .ToListAsync();
        }

        public async Task<IAsyncResult> MarkReservationAsPaid(int resID)
        {
            var reservation = await context.Reservations
                .SingleAsync(x => x.ID == resID);
            reservation.HasPaid = true;

            await context.SaveChangesAsync();
            
            return Task.CompletedTask;
        }
    }
}