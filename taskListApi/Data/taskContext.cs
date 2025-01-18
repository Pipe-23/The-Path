using Microsoft.EntityFrameworkCore;
using taskListApi.Models;

namespace taskListApi.Data
{
    public class taskContext : DbContext
    {
        public taskContext(DbContextOptions<taskContext> options) : base(options) { }

        public DbSet<taskItem> Tasks { get; set; }
    }
}
