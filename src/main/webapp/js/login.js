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
        firebase.auth.PhoneAuthProvider.PROVIDER_ID,
        firebase.auth.GoogleAuthProvider.PROVIDER_ID,
        firebaseui.auth.AnonymousAuthProvider.PROVIDER_ID,
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
    $(".name").text(user.displayName ? user.displayName : "Anonymous");
    $("#phone").text(user.phoneNumber);
    $("#email").text(user.email);
    user.photoURL
        ? $(".avatar").attr("src", user.photoURL)
        : $(".avatar").attr("src", "/image/img/user.svg");
    $(".comment").removeAttr("disabled");
    $("textarea.comment").attr("placeholder", "Type your comment.");

    if(!user.displayName){
        $('#user-name').attr("type", 'text');
        $("#user-name").attr("placeholder", 'Your name');
        //check phoneNumber but user can type his/her email as well.
        if(!user.phoneNumber){
            $("#user-contact").attr("type", "text");
            $("#user-contact").attr("placeholder", "Your contact");
        }
    }
}

function handleSignedOutUser() {
    ui.start("#firebaseui-auth-container", uiConfig);
    $(".user").addClass("d-none");
    $(".guest").removeClass("d-none");
    $(".comment").attr("disabled", "disabled");
    $("textarea.comment").attr("placeholder", "You have to login to post comment.");

}