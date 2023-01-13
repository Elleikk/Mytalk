console.log("안녕");

const userId = document.querySelector("#user");
const userPwd = document.querySelector("#password");
const loginBTN = document.querySelector("#loginBTN");


function testEvent(){
    console.log("클릭");
    
    const IDV = userId.value;
    const PWV = userPwd.value;
    
    
    let user = {
    		user : IDV,
    		pwd : PWV
    }
    
    console.log(user);
    
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

                let data = xhr.response;

                if(data == "true"){
					location.href="chatroom";
                }
                else if(data == "false"){
                    
                }

			}
		}
	}
		
	xhr.open("POST", "login/json", false);
	xhr.setRequestHeader("Content-type", "application/json");
	
	xhr.send(JSON.stringify(user));	

    // console.log(IDV);
    // console.log(PWV);
};

// const testEvent = () => {
//     console.log("클릭")
// };


loginBTN.addEventListener("click", testEvent);