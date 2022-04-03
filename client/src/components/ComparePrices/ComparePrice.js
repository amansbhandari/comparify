import React, { useEffect, useState } from "react";
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardHeader from '@mui/material/CardHeader';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';

import Typography from '@mui/material/Typography';
import GlobalStyles from '@mui/material/GlobalStyles';
import Container from '@mui/material/Container';
import logo from '../../assets/logo/logo-512-trans.png';
import { fetchComparedProducts } from "../../store/thunk/userThunkCreators";
import { useSelector } from "react-redux";

function transform(value) {


  return value.storeBrandList.map(itr => {
    return {
      price: itr.pricePerQuantity,
      store: itr.storeDetails.storeName,
      brand: itr.brandDetails.name,
      description: itr.brandDetails.description,

    }
  });
}
const ComparePrice = (props) => {
  
  const [tiers, handleTiers] = useState([])
  const item = useSelector(state => state.search.item);

  useEffect(() => {
    fetchComparedProducts(item).then((data) => {
      handleTiers(transform(data))
    })
      .catch(err => console.log("Axios err: ", err))
  }, [item])


     return (
      <React.Fragment>
        <GlobalStyles styles={{ ul: { margin: 0, padding: 0, listStyle: 'none' } }} />
        <CssBaseline />
        <Box>

          <Container disableGutters maxWidth="sm" component="main" sx={{ pt: 8, pb: 6 }}>
            <Typography
              component="h1"
              variant="h2"
              align="center"
              color="text.primary"
              gutterBottom
            >
            </Typography>
            <Box
              sx={{ display: 'flex', justifyContent: 'center', alignItems: 'baseline', mb: 2 }} />
          </Container>

          <Container maxWidth="md" component="main"  >
            <Grid container spacing={5} alignItems="flex-end">
              {tiers.map((tier) => (

                <Grid

                  item
                  key={tier.title}
                  xs={12}
                  md={4}

                >
                  <Card>
                    <CardHeader
                      title={tier.title}
                      titleTypographyProps={{ align: 'center' }}
                      subheaderTypographyProps={{
                        align: 'center',
                      }}
                      sx={{
                        backgroundColor: (theme) =>
                          theme.palette.mode === 'light'
                            ? theme.palette.grey[200]
                            : theme.palette.grey[700],
                      }}
                    />
                    <CardContent>
                      <Box
                        sx={{
                          display: 'flex',
                          justifyContent: 'center',
                          alignItems: 'baseline',
                          backgroundImage: `url(${logo})`,
                          mb: 2,
                        }}
                      >
                        <Typography component="h2" variant="h3" color="text.primary" >
                          ${tier.price}
                        </Typography>
                      </Box>
                      <ul style={{ "list-style-type": "none" }} align="center">
                        <p><b>Description:</b></p>{tier.description}
                      </ul>

                      <ul style={{ "list-style-type": "none" }}>
                        <Typography component="li" variant="body" align="center">
                          <p><b>Store:</b></p> {tier.store}
                        </Typography>
                      </ul>


                      <ul style={{ "list-style-type": "none" }}>
                        <Typography component="li" variant="body" align="center">
                          <p><b> Brand:</b></p> {tier.brand}
                        </Typography>
                      </ul>

                    </CardContent>
                    <CardActions>
                      <Button fullWidth variant={tier.buttonVariant = "outlined"}>
                        {tier.buttonText = "Deal of the day"}
                      </Button>
                    </CardActions>
                  </Card>
                </Grid>
              ))}
            </Grid>
          </Container>

        </Box>
      </React.Fragment>
    );

}

export default ComparePrice;
