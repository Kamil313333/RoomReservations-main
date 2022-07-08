using Microsoft.EntityFrameworkCore.Migrations;

namespace API.Migrations
{
    public partial class NewEdit : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ReservationRoom_Reservations_ReservationID",
                table: "ReservationRoom");

            migrationBuilder.DropForeignKey(
                name: "FK_ReservationRoom_Rooms_RoomID",
                table: "ReservationRoom");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ReservationRoom",
                table: "ReservationRoom");

            migrationBuilder.DropColumn(
                name: "HasPaid",
                table: "ReservationRoom");

            migrationBuilder.RenameTable(
                name: "ReservationRoom",
                newName: "ReservationRooms");

            migrationBuilder.RenameIndex(
                name: "IX_ReservationRoom_RoomID",
                table: "ReservationRooms",
                newName: "IX_ReservationRooms_RoomID");

            migrationBuilder.AddColumn<bool>(
                name: "HasPaid",
                table: "Reservations",
                type: "bit",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddPrimaryKey(
                name: "PK_ReservationRooms",
                table: "ReservationRooms",
                columns: new[] { "ReservationID", "RoomID" });

            migrationBuilder.AddForeignKey(
                name: "FK_ReservationRooms_Reservations_ReservationID",
                table: "ReservationRooms",
                column: "ReservationID",
                principalTable: "Reservations",
                principalColumn: "ID",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ReservationRooms_Rooms_RoomID",
                table: "ReservationRooms",
                column: "RoomID",
                principalTable: "Rooms",
                principalColumn: "ID",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ReservationRooms_Reservations_ReservationID",
                table: "ReservationRooms");

            migrationBuilder.DropForeignKey(
                name: "FK_ReservationRooms_Rooms_RoomID",
                table: "ReservationRooms");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ReservationRooms",
                table: "ReservationRooms");

            migrationBuilder.DropColumn(
                name: "HasPaid",
                table: "Reservations");

            migrationBuilder.RenameTable(
                name: "ReservationRooms",
                newName: "ReservationRoom");

            migrationBuilder.RenameIndex(
                name: "IX_ReservationRooms_RoomID",
                table: "ReservationRoom",
                newName: "IX_ReservationRoom_RoomID");

            migrationBuilder.AddColumn<bool>(
                name: "HasPaid",
                table: "ReservationRoom",
                type: "bit",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddPrimaryKey(
                name: "PK_ReservationRoom",
                table: "ReservationRoom",
                columns: new[] { "ReservationID", "RoomID" });

            migrationBuilder.AddForeignKey(
                name: "FK_ReservationRoom_Reservations_ReservationID",
                table: "ReservationRoom",
                column: "ReservationID",
                principalTable: "Reservations",
                principalColumn: "ID",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ReservationRoom_Rooms_RoomID",
                table: "ReservationRoom",
                column: "RoomID",
                principalTable: "Rooms",
                principalColumn: "ID",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
