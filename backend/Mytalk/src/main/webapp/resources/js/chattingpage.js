const contentBox = document.querySelector("#contentBox");
const chatInput = document.querySelector("#chatInput");
const postBTN = document.querySelector("#postBTN");


const chatting = document.querySelector(".chatting");
const yourchat = document.querySelector(".yourchat");

function contentBoxHeight(){
    console.log("height 함수 실행")
    const windowHeight = window.innerHeight;
    const contentHeight = windowHeight - 240;
    console.log(contentHeight);
    contentBox.style.height = contentHeight+"px";
};


function chattingWidth(){
    console.log("width 함수 실행");
    const chatWidth = chatting.clientWidth - 95;
    yourchat.style.width = chatWidth + "px";
    console.log(chatWidth);
};



function BTNOnOff(){
    if(chatInput.value == ""){
        postBTN.classList.remove("on");
    }
    else{
        postBTN.classList.add("on");
    }
};

function postChatting(){
    if(postBTN.className == "on"){
        console.log("클릭");
        const date = new Date();
        
        let sendTime = date.toLocaleString('ko-kr');
     
        
        console.log(sendTime);
       
        let chatData = {
            chat : chatInput.value,
            sendTime : sendTime
        }

        let xhr;
        if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
            xhr = new XMLHttpRequest();
        } else if (window.ActiveXObject) { // IE 6 이하
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
            
        xhr.onreadystatechange = function(){
            console.log(xhr.readyState);
            if(xhr.readyState == 4){
                console.log(xhr.status);
                if(xhr.status == 200){
                    console.log(xhr.response);
                    let data = JSON.parse(xhr.response);
                    console.log(data);

                    
                }
            }
        }
            
        xhr.open("POST", "chatting/json", true);
        xhr.setRequestHeader("Content-type", "application/json");
        
        xhr.send(JSON.stringify(chatData));	
        
        
    }
};





contentBoxHeight();
chattingWidth();
// window.onresize = contentBoxHeight;
// window.onresize = chattingWidth;
window.addEventListener("resize", contentBoxHeight);
window.addEventListener("resize", chattingWidth);

chatInput.addEventListener("input", BTNOnOff);
postBTN.addEventListener("click", postChatting);