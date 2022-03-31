import { Button, TextField, Box, Grid } from "@material-ui/core";
import { useFormik } from "formik";
import React from "react";
import { useDispatch } from "react-redux";
import { signup } from "../store/thunk/userThunkCreators"
import useStyles from "../hooks/use-styles";
import { MenuItem, Select } from "@material-ui/core";


  const style = {
    root: {
        display: "inline-block",
        margin: "30px 0px"
      
       },
    button: {
        marginTop: "16px",
        marginBottom: "8px"
    },
    buttonContainer: {
        display: "flex",
        justifyContent: "end"
    }
};


const RegisterPage = (props) => {

    const classes = useStyles(style);
    const dispatch = useDispatch();

    const [question1, setQn1] = React.useState('');
    const [question2, setQn2] = React.useState('');
    const [question3, setQn3] = React.useState('');

    const [A1, getans1] = React.useState('');
    const [A2, getans2] = React.useState('');
    const [A3, getans3] = React.useState('');

    const handleSelect1 = event => {
        setQn1(event.target.value);
    };
    const handleSelect2 = event => {
        setQn2(event.target.value);
    };
    const handleSelect3 = event => {
        setQn3(event.target.value);
    };

    const ans1 = event => {
        getans1(event.target.value);
    };
    const ans2 = event => {
        getans2(event.target.value);
    };
    const ans3 = event => {
        getans3(event.target.value);
    };

    const formik = useFormik({
        initialValues: {
            firstname:'',
            lastname:'',
            username:'',
            email:'',
            password:'',
            confirmpassword:''
        },
        onSubmit: values => {
            console.log('clicked');
            const { firstname, lastname, username, email, password, confirmpassword } = values;

            if(!firstname || !lastname || !username || !email || !question1 || !question2 || !question3 || !A1 || !A2 || !A3 || !password || !confirmpassword){
             console.log(formik.values)
                return;
            }
        
            const newValues = {
                firstName: firstname,
                lastName: lastname,
                username: username,
                email: email,
                securityQuestion1: question1,
                securityQuestion2: question2,
                securityQuestion3: question3,
                Answer1: A1,
                Answer2: A2,
                Answer3: A3,
                password: password,
                confirmPassword: confirmpassword
            }
            
            dispatch(signup(newValues));

        }
    });

    return (
        
        <Grid container className={classes.root}>
           <Box style={{width: '100%'}}>
                <form onSubmit={formik.handleSubmit}>
    
                    <Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="firstname"
                                name="firstname"
                                label="First Name"
                                autoComplete="off"
                                value={formik.values.firstname}
                                onChange={formik.handleChange}
                                error={formik.touched.firstname && Boolean(formik.errors.firstname)}
                                helperText={formik.touched.firstname && formik.errors.firstname}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="lastname"
                                name="lastname"
                                label="Last Name"
                                autoComplete="off"
                                value={formik.values.lastname}
                                onChange={formik.handleChange}
                                error={formik.touched.lastname && Boolean(formik.errors.lastname)}
                                helperText={formik.touched.lastname && formik.errors.lastname}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="username"
                                name="username"
                                label="UserName"
                                autoComplete="off"
                                value={formik.values.username}
                                onChange={formik.handleChange}
                                error={formik.touched.username && Boolean(formik.errors.username)}
                                helperText={formik.touched.username && formik.errors.username}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="email"
                                name="email"
                                label="Email"
                                autoComplete="off"
                                value={formik.values.email}
                                onChange={formik.handleChange}
                                error={formik.touched.email && Boolean(formik.errors.email)}
                                helperText={formik.touched.email && formik.errors.email}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <Grid item>
                            <div align="left">
                                <h3>
                                    Security Questions:
                                </h3>
                                 {/* 1st Security Question */}
                                <h3>
                                    Security question 1
                                </h3>
                                {/* //select question */}
                                <Select
                                    id="Question1select" fullWidth margin="normal" 
                                    editable={false}
                                    value={question1} 
                                    onChange={handleSelect1}
                                   
                                    
                                >
                                    <MenuItem value={"What was your childhood nickname?"}>What was your childhood nickname?</MenuItem>
                                    <MenuItem value={"What is your favourite sport?"}>What is your favourite sport?</MenuItem>
                                    <MenuItem value={"What school did you attend?"}>What school did you attend?</MenuItem>
                                    <MenuItem value={"What is the name of your oldest sibling?"}>What is the name of your oldest sibling ?</MenuItem>
                                    <MenuItem value={"What is the name of your favorite childhood friend?"}>What is the name of your favorite childhood friend?</MenuItem>


                                </Select>

                                {/* //input answer */}
                                <input type="text" onChange={ans1} className="register" id="security1ans"  minlength="4" maxlength="20"
                                    placeholder="Must be a minimum of 4 characters long" required/>
                                <span class="validity"></span>


                                {/* // 2nd Security Question */}
                                <h3>
                                    Security question 2
                                </h3>
                                {/* //select question  */}
                                <Select
                                id="Questionselect2"
                                    fullWidth margin="normal" 
                                    value={question2} 
                                    onChange={handleSelect2}
                                
                                >
                                    <MenuItem value={"What was your childhood nickname?"}>What was your childhood nickname?</MenuItem>
                                    <MenuItem value={"What is your favourite sport?"}>What is your favourite sport?</MenuItem>
                                    <MenuItem value={"What school did you attend?"}>What school did you attend?</MenuItem>
                                    <MenuItem value={"What is the name of your oldest sibling?"}>What is the name of your oldest sibling ?</MenuItem>
                                    <MenuItem value={"What is the name of your favorite childhood friend?"}>What is the name of your favorite childhood friend?</MenuItem>


                                </Select>

                                {/* //input answer */}
                                <input type="text" onChange={ans2} className="register" id="security2ans"  minlength="4" maxlength="20"
                                placeholder="Must be a minimum of 4 characters long" required/>
                                <span class="validity"></span>

                                {/* // 3rd Security Question */}
                                <h3>
                                    Security question 3
                                </h3>
                                {/* //select question */}
                                <Select 
                                    fullWidth margin="normal" 
                                    value={question3} 
                                    onChange={handleSelect3}
                                
                                 >
                                    <MenuItem value={"What was your childhood nickname?"}>What was your childhood nickname?</MenuItem>
                                     <MenuItem value={"What is your favourite sport?"}>What is your favourite sport?</MenuItem>
                                     <MenuItem value={"What school did you attend?"}>What school did you attend?</MenuItem>
                                     <MenuItem value={"What is the name of your oldest sibling?"}>What is the name of your oldest sibling ?</MenuItem>
                                     <MenuItem value={"What is the name of your favorite childhood friend?"}>What is the name of your favorite childhood friend?</MenuItem>
                                </Select>
                                {/* //input answer */}
                                <input type="text" onChange={ans3} className="register" id="security3ans"  minlength="4" maxlength="20"
                                placeholder="Must be a minimum of 4 characters long" required/>
                                <span class="validity"></span>
                            </div>
                        </Grid> 
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="password"
                                name="password"
                                label="Password"
                                autoComplete="off"
                                type="password"
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                helperText={formik.touched.password && formik.errors.password}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                        <div>Must be a minimum 8 characters with atleast one uppercase, lowercase, punctuation and symbol</div>
                        <Grid item>
                            <TextField fullWidth margin="normal" 
                                id="confirmpassword"
                                name="confirmpassword"
                                label="ConfirmPassword"
                                autoComplete="off"
                                type="password"
                                value={formik.values.confirmpassword}
                                onChange={formik.handleChange}
                                error={formik.touched.confirmpassword && Boolean(formik.errors.confirmpassword)}
                                helperText={formik.touched.confirmpassword && formik.errors.confirmpassword}
                                inputProps={{
                                    autoComplete: 'off'
                                }}
                            />
                        </Grid>
                    
                        <Grid item className={classes.buttonContainer}>
                            <Button color="primary" variant="contained" type="submit" className={classes.button} >
                                Register
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </Grid>
        
        );

    
}
export default RegisterPage;

