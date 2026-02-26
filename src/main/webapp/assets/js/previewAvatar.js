function previewImage(input){
    if(input.files && input.files[0]){
        const reader = new FileReader();
        reader.onload = e => {
            document.getElementById("previewAvatar").src = e.target.result;
        }
        reader.readAsDataURL(input.files[0]);
    }
}/**
 * 
 */