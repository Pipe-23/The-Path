using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using taskListApi.Data;
using taskListApi.Models;

namespace taskListApi.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TasksController : ControllerBase
    {
        private readonly taskContext _context;

        public TasksController(taskContext context)
        {
            _context = context;
        }

        // Obtener todas las tareas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<taskItem>>> GetTasks()
        {
            return await _context.Tasks.ToListAsync();
        }

        // Obtener una tarea por ID
        [HttpGet("{id}")]
        public async Task<ActionResult<taskItem>> GetTask(int id)
        {
            var task = await _context.Tasks.FindAsync(id);
            if (task == null)
            {
                return NotFound();
            }
            return task;
        }

        // Crear una nueva tarea
        [HttpPost]
        public async Task<ActionResult<taskItem>> CreateTask(taskItem task)
        {
            _context.Tasks.Add(task);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetTask), new { id = task.Id }, task);
        }

        // Actualizar una tarea existente
        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateTask(int id, taskItem updatedTask)
        {
            if (id != updatedTask.Id)
            {
                return BadRequest();
            }

            _context.Entry(updatedTask).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!_context.Tasks.Any(e => e.Id == id))
                {
                    return NotFound();
                }
                throw;
            }

            return NoContent();
        }

        // Eliminar una tarea
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTask(int id)
        {
            var task = await _context.Tasks.FindAsync(id);
            if (task == null)
            {
                return NotFound();
            }

            _context.Tasks.Remove(task);
            await _context.SaveChangesAsync();

            return NoContent();
        }
    }
}
