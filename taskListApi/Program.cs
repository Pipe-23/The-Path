using Microsoft.EntityFrameworkCore;
using taskListApi.Data;

var builder = WebApplication.CreateBuilder(args);

// Configurar la base de datos SQLite
builder.Services.AddDbContext<taskContext>(options =>
    options.UseSqlite("Data Source=tasks.db"));

builder.Services.AddControllers();

var app = builder.Build();

// Crear la base de datos al iniciar la aplicación (si no existe)
using (var scope = app.Services.CreateScope())
{
    var dbContext = scope.ServiceProvider.GetRequiredService<taskContext>();
    dbContext.Database.EnsureCreated();
}

app.MapControllers();
app.Run();
