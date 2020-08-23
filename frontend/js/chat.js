const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();
let Language;
let otherlanguage;
function connectToChat(userName) {

    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            if (selectedUser === data.sender) {
                render(data.message, data.sender);
            } else {
                newMessages.set(data.sender, data.message);
                $('#userNameAppender_' + data.sender).append('<span id="newMessage_' + data.sender + '" style="color: red">+1</span>');
            }
        });
    });
}


function sendMsg(from, text) {

    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        sender: from,
        message: text,
        recipient: selectedUser,
        mylanguage: Language,
        otherlanguage: otherlanguage
    }));

}

function registration() {
    let userName = document.getElementById("userName").value;
    Language = document.getElementById("Language").value;

    $.get(url + "/registration/"+userName+"/"+Language, function (response) {

        connectToChat(userName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function selectUser(userName) {

    selectedUser = userName;

    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}
function otherLanguage(othlanguage) {
    otherlanguage=othlanguage;

}

function fetchAll() {
    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i].username + '\');otherLanguage(\'' + users[i].languagecode+ '\');"><li class="clearfix">\n' +
                '                <img src="./images/user.png" width="55px" height="55px" alt="avatar" />\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + users[i].username + '" class="name">' + users[i].username + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}

function fetchMessages() {

}
