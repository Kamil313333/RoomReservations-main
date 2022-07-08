using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API.DTOs
{
    public class ReservationDTO
    {
        public int DoorNum { get; set; }
        public int ReservationID { get; set; }
        public int GuestCount { get; set; }
        public int DaysToStay { get; set; }
        public float PricePerDay { get; set; }
        public bool HasPaid { get; set; }
        public string ImageURL { get; set; }
        public string DateFrom { get; set; }
        public string DateTo { get; set; }
    }
}