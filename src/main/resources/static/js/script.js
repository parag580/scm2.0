console.log("script loaded");

let currentTheme=getTheme();
console.log(currentTheme);
document.addEventListener('DOMContentLoaded',()=>{
    changeTheme();
})

function changeTheme(){
    document.querySelector('html').classList.add(currentTheme);
    //set the listner to change theme
    const changeThemeButton=document.querySelector('#theme_change_button')


    changeThemeButton.addEventListener("click",(event) => {
        const oldTheme=currentTheme;
        console.log("change theme button clicked");
       
       if(currentTheme==="dark"){
             currentTheme="light";
       }else{
             currentTheme="dark";
       }

       //localStorage update
       setTheme(currentTheme);
       //remove the currenttheme
       document.querySelector('html').classList.remove(oldTheme);
       document.querySelector('html').classList.add(currentTheme);

       //change the text of button
       console.log(currentTheme);
       
       changeThemeButton.querySelector('span').textContent=currentTheme=="light"?"Dark":"Light";

    });
}

//set theme to localstorage
   function setTheme(theme){
    localStorage.setItem("theme",theme);
   }
   //get theme to localstorage
   function getTheme(){
    let theme=localStorage.getItem("theme");
    return theme?theme:"Light";
   }
   
   