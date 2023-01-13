const signupId = document.querySelector("#signupId");
const signupPwd = document.querySelector("#signupPwd");
const signupBTN = document.querySelector("#signupBTN");

const over_span = document.getElementById("over"); 

const signupEmail = document.querySelector("#email");
const EmailBTN = document.querySelector("#EmailBTN");

const confirm = document.querySelector("#confirm");
const confirmBTN = document.querySelector("#confirmBTN");

const signupCPwd = document.querySelector("#signupCPwd");
const check = document.querySelector("#check");

let checkNum = null;

let checkOK = false;

let checkPWD = false;

function postSignup(){

	if(over_span.innerText ==  "사용 가능한 아이디입니다" && checkOK == true && checkPWD == true){
		console.log("클릭");

		let ID = signupId.value;
		let PWD = signupPwd.value;

		console.log("user ID :" + ID + " userPWD:" + PWD);
		
		let signup = {
			ID : ID,
			PWD : PWD
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

					let data = xhr.response;

					if (data == "true"){
						alert("회원가입이 완료되었습니다");
						location.href="login";
					}
					else{
						alert("회원가입이 정상 처리되지 않았습니다");
					}
				}
			}
		}
			
		xhr.open("POST", "signup/json", false);
		xhr.setRequestHeader("Content-type", "application/json");
		
		xhr.send(JSON.stringify(signup));
	}
	else if(checkPWD == false){
		alert("비밀번호가 일치하지 않습니다")
	}
	else if(checkOK == false){
		alert("인증번호 확인 완료해주세요");
	}

}

function postUserID(){

	if(signupId.value == ""){

	}
	else{
		let userID = {
				userID : signupId.value
			};
		console.log(userID);
		
		
	
	
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
						over_span.className="true";
						over_span.innerText = "사용 가능한 아이디입니다";
					}
					else if(data == "false"){
						over_span.className = "";
						over_span.innerText = "아이디가 중복되었습니다";
					}
					console.log(over_span);
	
				}
			}
		}
			
		xhr.open("POST", "signup/selectID", true);
		xhr.setRequestHeader("Content-type", "application/json");
		
		xhr.send(JSON.stringify(userID));	
	}



}


function postEmail(){
	if(signupEmail.value == ""){

	}
	else{
		console.log("postEmail");
		let userEmail = {
			userEmail : signupEmail.value
		};



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
					
					console.log(typeof data);
					checkNum = data;

				}
			}
		}
			
		xhr.open("POST", "signup/postEmail", true);
		xhr.setRequestHeader("Content-type", "application/json");
		
		xhr.send(JSON.stringify(userEmail));	
	}
};

function postConfirm(){
	console.log("postConfirm");
	console.log(checkNum);

	if(confirm.value == checkNum){
		alert("인증번호 확인 완료");
		checkOK = true;
	}
	
};


function checkPassword(){
	let Pwd = signupPwd.value;
	let CPwd = signupCPwd.value;

	console.log(Pwd);
	console.log(CPwd);

	if(Pwd == "" || CPwd == "" ){
		checkPWD = false;
	}
	else if(Pwd == CPwd){
		checkPWD = true;
		
	}
	else{
		checkPWD = false;	
	}


	if(checkPWD == true){
		check.className = "true";
		check.innerText = "비밀번호가 일치합니다.";
	}
	else if(Pwd == "" && CPwd == ""){
		check.innerText = "";
	}
	else{
		check.className = "";
		check.innerText = "비밀번호가 일치하지 않습니다.";
	}
};


signupId.addEventListener("blur", postUserID);
signupBTN.addEventListener("click", postSignup);

EmailBTN.addEventListener("click", postEmail);
confirmBTN.addEventListener("click", postConfirm);

signupPwd.addEventListener("blur", checkPassword);
signupCPwd.addEventListener("blur", checkPassword);