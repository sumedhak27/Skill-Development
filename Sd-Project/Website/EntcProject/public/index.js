var config = {
    apiKey: "AIzaSyBM5QSzk8_ePeLPy0Biq6vXwySVGyYJ1VA",
    authDomain: "studentdatabase-e40b3.firebaseapp.com",
    databaseURL: "https://studentdatabase-e40b3.firebaseio.com",
    projectId: "studentdatabase-e40b3",
    storageBucket: "studentdatabase-e40b3.appspot.com",
    messagingSenderId: "558067736957"
};
firebase.initializeApp(config);

var db = firebase.firestore();
var stuRef = db.collection("students");

// const submit = document.querySelector("#submit")

// submit.addEventListener("click",
 async function submitFunction(){

    const fname = document.querySelector("#fname").value;
    const mname = document.querySelector("#mname").value;
    const lname = document.querySelector("#lname").value;
    const grno = document.querySelector("#grno").value;
    const rollno = document.querySelector("#rollno").value;
    const acedemicyear = document.querySelector('#acedemicyear').value
    const shift = document.querySelector('#shift').value
    const branch = document.querySelector('#branch').value
    const bdate = document.querySelector('#bdate').value
    const mobile = document.querySelector('#mobile').value
    const email = document.querySelector('#email').value
    const caste = document.querySelector('#caste').value

    const fathername = document.querySelector('#fathername').value
    const fatherprofession = document.querySelector('#fatherprofession').value
    const fatherdesignation = document.querySelector('#fatherdesignation').value
    const fathermobile = document.querySelector('#fathermobile').value
    const fatheremail = document.querySelector('#fatheremail').value
    const mothername = document.querySelector('#mothername').value
    const motherprofession = document.querySelector('#motherprofession').value
    const motherdesignation = document.querySelector('#motherdesignation').value
    const mothermobile = document.querySelector('#mothermobile').value
    const motheremail = document.querySelector('#motheremail').value

    const perAddress = document.querySelector('#perAddress').value
    const perPinCode = document.querySelector('#perPinCode').value;
    const perAreas = document.getElementsByName('perArea')
    const perState  = document.getElementById('perState').value;
    const perCity  = document.getElementById('perCity').value;
    let perArea = null;
    if(perAreas[0].checked)
        perArea = perAreas[0]
    else
        perArea = perAreas[1]
    const currAddress = document.querySelector('#currAddress').value
    const currState  = document.getElementById('currState').value;
    const currCity  = document.getElementById('currCity').value;
    const currPinCode = document.querySelector('#currPinCode').value;
    const currAreas = document.getElementsByName('currArea')
    let currArea = null
    if(currAreas[0].checked)
        currArea = currAreas[0]
    else
        currArea = currAreas[1]

    const tenthmarks = document.querySelector('#tenthMarks').value
    const twelthmarks = document.querySelector('#twelthMarks').value
    const fycgpa = document.querySelector('#fycgpa').value
    const sycgpa = document.querySelector('#sycgpa').value
    const tycgpa = document.querySelector('#tycgpa').value
    const becgpa = document.querySelector('#becgpa').value
    const skills = document.querySelector('#skills').value
    const internships = document.getElementById('internships').value

    let lnameU = lname.toUpperCase()
    let mnameU = mname.toUpperCase() 
    let fnameU = fname.toUpperCase()
    let grnoU = grno.toUpperCase()
    let fathernameU = fathername.toUpperCase()
    let mothernameU = mothername.toUpperCase()

    if(fname == '' || mname == "" || lname =="" || rollno == "" || acedemicyear=="" || shift=="" || branch=="" || bdate=="" || mobile=="" || email=="" || caste=="" || fathername=="" || fatherprofession=="" || fathermobile=="" || fatheremail=="" || mothername=="" || motherprofession=="" || mothermobile=="" || motheremail=="" || perAddress=="" || perState=="" || perCity =="" ||perPinCode==""||currAddress===""||currCity==""||currState==""||currPinCode==""||tenthmarks=="" || twelthmarks=="" ){
        return
    }
    // if(mobile.lenght() !=10 || fathermobile.lenght() != 10 || mothermobile.lenght() != 10){
    //     alert("Mobile number entered is incorrect.")
    //     return
    // }
    if(tenthmarks>100||tenthmarks<0){
        alert("10th marks are incorrectly filled.")
        return
    }
    if(twelthmarks>100||twelthmarks<0){
        alert("12th marks are incorrectly filled.")
        return
    }
    if(fycgpa>10||fycgpa<0){
        alert("First Year cgpa is incorrectly filled.")
        return
    }
    if(sycgpa>10||sycgpa<0){
        alert("Second Year cgpa is incorrectly filled.")
        return
    }
    if(tycgpa>10||tycgpa<0){
        alert("Third Year cgpa is incorrectly filled.")
        return
    }
    if(becgpa>10||becgpa<0){
        alert("Fourth Year cgpa is incorrectly filled.")
        return
    }
    // if(acedemicyear === "SYBtech" && fycgpa==""){
    //     alert("Please fill First year cgpa.")
    //     return
    // }
    // if(acedemicyear === "T.E" && (sycgpa=="" || fycgpa=="")){
    //     alert("First year and Second year cgpa are compulsary.")
    //     return
    // }
    // if(acedemicyear === "B.E" && (sycgpa=="" || fycgpa=="" || tycgpa=="")){
    //     alert("First year, Second year and Third year cgpa are compulsary.")
    //     return
    // }
    

    stuRef.where('personalInfo.grNo', '==', grnoU).get()
    .then(result => {
        if(result.empty){
            console.log('new entry...')
            stuRef.doc(grnoU).set({
                personalInfo : {                  
                    firstName: fnameU,
                    middleName: mnameU,
                    lastName: lnameU,
                    grNo: grnoU,
                    rollNo: rollno,
                    academicYear : acedemicyear,
                    shift:shift,
                    branch: branch,
                    birthDate: bdate,
                    mobile: mobile,
                    email: email,
                    caste : caste
                },
                father:{
                    name: fathernameU,
                    profession: fatherprofession,
                    designation: fatherdesignation,
                    mobileNo: fathermobile,
                    email: fatheremail
                },
                mother:{
                    name: mothernameU,
                    profession: motherprofession,
                    designation: motherdesignation,
                    mobileNo: mothermobile,
                    email: motheremail
                },
                permanentAddress:{
                    address:perAddress,
                    state: perState,
                    city: perCity,
                    pinCode: perPinCode,
                    area : perArea.value
                },
                currentAddress:{
                    address:currAddress,
                    state: currState,
                    city: currCity,
                    pinCode: currPinCode,
                    area : currArea.value
                },
                academicDetails:{
                    '10th Marks': tenthmarks,
                    '12th Marks': twelthmarks,
                    fycgpa: fycgpa,
                    sycgpa: sycgpa,
                    tycgpa: tycgpa,
                    becgpa: becgpa
                },
                skills : skills,
                internships : internships
            })
            .then(() => {
                alert('Details Recorded Successfully.')
            })
            .catch( err => console.log(err))   
        }
        else{
            var r = confirm("You already have an entry! \n Do you want to update the info?")
            if(r == true){
                stuRef.doc(grnoU).set({
                    personalInfo : {                  
                        firstName: fnameU,
                        middleName: mnameU,
                        lastName: lnameU,
                        grNo: grnoU,
                        rollNo: rollno,
                        academicYear : acedemicyear,
                        shift:shift,
                        branch: branch,
                        birthDate: bdate,
                        mobile: mobile,
                        email: email,
                        caste : caste
                    },
                    father:{
                        name: fathernameU,
                        profession: fatherprofession,
                        designation: fatherdesignation,
                        mobileNo: fathermobile,
                        email: fatheremail
                    },
                    mother:{
                        name: mothernameU,
                        profession: motherprofession,
                        designation: motherdesignation,
                        mobileNo: mothermobile,
                        email: motheremail
                    },
                    permanentAddress:{
                        address:perAddress,
                        state: perState,
                        city: perCity,
                        pinCode: perPinCode,
                        area : perArea.value
                    },
                    currentAddress:{
                        address:currAddress,
                        state: currState,
                        city: currCity,
                        pinCode: currPinCode,
                        area : currArea.value
                    },
                    academicDetails:{
                        '10th Marks': tenthmarks,
                        '12th Marks': twelthmarks,
                        fycgpa: fycgpa,
                        sycgpa: sycgpa,
                        tycgpa: tycgpa,
                        becgpa: becgpa
                    },
                    skills : skills,
                    internships : internships
                })
                .then(() => {
                    alert('Details Recorded Successfully.')
                })
                .catch( err => console.log(err)) 
            }
            else{
                console.log('Done nothing..')
            }
        }
    })
    .catch( err => console.log(err))

    return false
}

const checkbox = document.getElementById('isAddressSame');
checkbox.addEventListener('click', () => {
    console.log('checkbox clicked..')
    if(checkbox.checked == true){
        console.log('checked box...');
        document.getElementById("currAddress").value = document.getElementById("perAddress").value;
        document.getElementById("currPinCode").value = document.querySelector('#perPinCode').value;
        document.getElementById("currState").value = document.getElementById("perState").value;
        document.getElementById("currCity").value = document.getElementById("perCity").value;
        document.getElementsByName('currArea')[0].checked = document.getElementsByName('perArea')[0].checked;
        document.getElementsByName('currArea')[1].checked = document.getElementsByName('perArea')[1].checked;
    }
    else{
        document.getElementById("currAddress").value = null;
        document.getElementById("currPinCode").value = null;
        document.getElementById("currState").value = null;
        document.getElementById("currCity").value = null;
        document.getElementsByName('currArea')[0].checked = true;
    }

})