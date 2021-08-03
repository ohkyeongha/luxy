   window.onload = function(){
      
      var saveBtn = document.getElementById("board-save-btn");
      var cancelBtn = document.getElementById("board-cancel-btn");
      
      //저장버튼 클릭이벤트
      saveBtn.onclick = function(){
         var bTitle = document.getElementById("bTitle");
         var bContent = document.getElementById("bContent");
         
         if(bTitle.value==""){
            alert("제목을 입력하세요.");
            bTitle.focus();
            return;
         }
         if(bContent.value==""){
            alert("내용을 입력하세요.");
            bContent.focus();
            return;
         }
         
         //name값은 바로 사용 가능
         document.boardWriteForm.submit();
      }
      cancelBtn.onclick = function(){
         window.history.back();
         
      }
      
   }