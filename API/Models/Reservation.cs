using System.Collections.Generic;

namespace API.Models
{
    public class Reservation
    {
        public int ID {get; set;}
        public int UserID {get; set;}
        public int RoomId {get; set;}
        public int GuestsCount {get; set;}
        public string DateFrom {get; set;}
        public string DateTo {get; set;}
        public bool HasPaid {get; set;}
        public virtual User User {get; set;}
        public virtual Room Room {get; set;}
    }
}