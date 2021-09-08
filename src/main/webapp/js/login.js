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

    $(".user-name").text(user.displayName ? user.displayName : "Anonymous");
    $("p.user-contact").text(user.email ? user.email : user.phoneNumber);
    $("img.user-avatar").attr("src", user.photoURL ? user.photoURL : "/image/img/user.svg");


    $("input.user-name").val(user.displayName);
    if (user.displayName) {
        $("input.user-name").attr("readonly", " readonly");
    }

    $("input.user-contact").val(user.email ? user.email : user.phoneNumber);
    $("input.user-contact").attr("readonly", "readonly");

    $(".comment").removeAttr("disabled");
    $("textarea.comment").attr("placeholder", "Type your comment.");

    $("button.add-post").removeAttr("disabled");
    $("textarea.post-content").attr("placeholder", "Write your review.");
}

function handleSignedOutUser() {
    ui.start("#firebaseui-auth-container", uiConfig);
    $(".user").addClass("d-none");
    $(".guest").removeClass("d-none");
    $(".comment").attr("disabled", "disabled");
    $("textarea.comment").attr("placeholder", "You have to login to post comment.");
    $("button.add-post").attr("disabled", "disabled");
    $("textarea.post-content").attr("placeholder", "You have to login to post your review.");
}