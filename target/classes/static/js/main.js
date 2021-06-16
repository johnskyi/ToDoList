$(function(){

    const appendTask = function(data){
             var taskCode = '<li class="task-li" id="'+data.id+'"><span class="task-text" >'+ data.task +
                 '&nbsp;&nbsp;<span class="icon"><i id="delete-task" class="fas fa-trash-alt" data-id="' + data.id +'"></i></span>'+
             '&nbsp;&nbsp;<span class="icon"><i id="check" class="fas fa-check" data-id="' + data.id +'"></i></span></span></li>';
        $('#task-list')
            .append(taskCode);
    };

//    Loading tasks on load page
    $.get('/tasks/', function(response)
    {
        for(i in response) {
            appendTask(response[i]);
        }
    });

    //Show adding task form
    $('#pencil').click(function(){
        $('#task-form').css('display', 'flex');
    });

    //Closing adding task form
        $('#task-form').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
        });

     //Checked task
      $(document).on('click', '#check', function(){
                 var buttonCheck = $(this);
                 var taskId = buttonCheck.data('id');
                 $.ajax({
                     method: "PUT",
                     url: '/tasks/' + taskId,
                     success: function()
                     {
                         $('.task-li#' + taskId).css('text-decoration',' line-through');
                     }
                 });
                 return false;
             });

    //Adding task
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['task']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });

     //Delete task
        $(document).on('click', '#delete-task', function(){
            var buttonDelete = $(this);
            var taskId = buttonDelete.data('id');
            $.ajax({
                method: "DELETE",
                url: '/tasks/' + taskId,
                success: function()
                {
                    $('.task-li#' + taskId).remove();
                }
            });
            return false;
        });

         //Delete all tasks
                $(document).on('click', '#deleteAll', function(){

                    $.ajax({
                        method: "DELETE",
                        url: '/tasks/',
                        success: function()
                        {
                            $('#task-list').empty();
                        }
                    });
                    return false;
                });

});