using Microsoft.EntityFrameworkCore.Migrations;

namespace API.Migrations
{
    public partial class ReservationRoomEdit : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "HasPaid",
                table: "ReservationRoom",
                type: "bit",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "HasPaid",
                table: "ReservationRoom");
        }
    }
}
