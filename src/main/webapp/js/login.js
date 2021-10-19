const firebaseConfig = {
    apiKey: "AIzaSyBZXCPGeihQk4el2aKjTcA_iOMtHDgwd_I",
    authDomain: "mflix-281ce.firebaseapp.com",
    projectId: "mflix-281ce",
    storageBucket: "mflix-281ce.appspot.com",
    messagingSenderId: "129939925646",
    appId: "1:129939925646:web:9712299875aa7bdb4db7df",
    measurementId: "G-HYPR3TE69L"
};
firebase.initializeApp(firebaseConfig);
firebase.auth().onAuthStateChanged(function (user) {
    user ? handleSignedInUser(user) : handleSignedOutUser();
});

const ui = new firebaseui.auth.AuthUI(firebase.auth());
const uiConfig = {
    signInFlow: "popup",
    signInOptions: [
        firebase.auth.EmailAuthProvider.PROVIDER_ID,
        {
            provider: firebase.auth.PhoneAuthProvider.PROVIDER_ID,
            defaultCountry: 'VN',
        },
        firebase.auth.GoogleAuthProvider.PROVIDER_ID,
    ],
    callbacks: {
        signInSuccessWithAuthResult: function (authResult) {
            if (authResult.user) {
                handleSignedInUser(authResult.user);
            }
            return false;
        }
    }
};
ui.start("#firebaseui-auth-container", uiConfig);

function handleSignedInUser(user) {
    $(".guest").addClass("d-none");
    $(".user").removeClass("d-none");

    if(user.email === "phamtrunghieu6d@gmail.com"){
        $("li#manager-form").css("display", "block");
        $("input.uid").val(user.uid);
        $("li#manager-form > form > button").removeAttr("disabled");
    }

    $(".user-name").text(user.displayName ? user.displayName : "Anonymous");
    $("img.user-avatar").attr("src", user.photoURL ? user.photoURL : "/image/img/user.svg");

    $("input.user-name").val(user.displayName ? user.displayName : 'Anonymous');
    $("input.user-contact").val(user.email ? user.email : user.phoneNumber);

    $(".comment").removeAttr("disabled");
    $(".comment").attr("placeholder", "Type your comment.");

}

function handleSignedOutUser() {
    ui.start("#firebaseui-auth-container", uiConfig);
    $(".user").addClass("d-none");
    $(".guest").removeClass("d-none");

    $(".comment").attr("disabled", "disabled");
    $(".comment").attr("placeholder", "You have to login to post comment.");

}
