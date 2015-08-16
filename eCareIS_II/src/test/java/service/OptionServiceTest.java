package service;


import dao.OptionDAO;
import entity.Option;
import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import exceptions.OptionInconsistencyImpossibleException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import service.DTO.OptionDTO;
import service.impl.OptionServiceImpl;
import utils.Mappers.OptionMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class OptionServiceTest {

    @Autowired
    OptionDAO optionDAO;

    @Autowired
    OptionService optionService;

    @Test
    public void getOptionByIdTest(){
        Integer nonExistingOptionId = 100000;
        Option option1 = createOption(1,"sms100",100,200);
        when(optionDAO.get(1)).thenReturn(option1);
        Assert.assertEquals(OptionMapper.EntityToDTO(option1), optionService.getOptionById(1));
        Assert.assertNull(optionService.getOptionById(null));
    }

    @Test
    public void getAllOptionsTest(){
        Option option1 = createOption(1,"sms100",100,200);
        Option option2 = createOption(2,"sms200",200,300);
        List<Option> options = new ArrayList<Option>();
        options.add(option1);
        options.add(option2);
        Set<Option> optionSet = new HashSet<Option>(options);
        when(optionDAO.getAll()).thenReturn(options);
        Assert.assertEquals(OptionMapper.EntitySetToDTOSet(optionSet), optionService.getAllOptions());
    }

    @Test
    public void addOption() {
        Option option1 = createOption(1, "sms100", 100, 200);
        optionService.addOption(OptionMapper.EntityToDTO(option1));
        verify(optionDAO,times(1)).add(option1);
    }

    @Test
    public void getDependentOptionTreeTest(){
        Option option1 = createOption(1,"sms Пакет",100,200);
        Option option2 = createOption(2,"sms 100",200,300);
        Option option3 = createOption(3,"sms 100 PLUS",200,300);
        option1.getDependentOption().add(option2);
        option2.getDependentOption().add(option3);
        when(optionDAO.get(1)).thenReturn(option1);
        when(optionDAO.get(2)).thenReturn(option2);
        when(optionDAO.get(3)).thenReturn(option3);
        Set<OptionDTO> optionSet = new HashSet<OptionDTO>();
        optionSet.add(OptionMapper.EntityToDTO(option1));
        optionSet.add(OptionMapper.EntityToDTO(option2));
        optionSet.add(OptionMapper.EntityToDTO(option3));
        Assert.assertEquals(optionService.getDependentOptionTree(1), optionSet);
    }

    @Test
    public void getRequiredOptionTreeTest(){
        Option option1 = createOption(1,"sms Пакет",100,200);
        Option option2 = createOption(2,"sms 100",200,300);
        Option option3 = createOption(3,"sms 100 PLUS",200,300);
        option2.getRequiredOption().add(option1);
        option3.getRequiredOption().add(option2);
        when(optionDAO.get(1)).thenReturn(option1);
        when(optionDAO.get(2)).thenReturn(option2);
        when(optionDAO.get(3)).thenReturn(option3);
        Set<OptionDTO> optionSet = new HashSet<OptionDTO>();
        optionSet.add(OptionMapper.EntityToDTO(option1));
        optionSet.add(OptionMapper.EntityToDTO(option2));
        optionSet.add(OptionMapper.EntityToDTO(option3));
        Assert.assertEquals(optionService.getRequiredOptionTree(3), optionSet);
    }
    @Test
    public void isOptionsConsistentIncludingAllRequired_true(){
        Option option11 = createOption(1,"sms Пакет1",100,200);
        Option option12 = createOption(2,"sms 100",200,300);
        Option option21 = createOption(3,"sms Пакет2",100,200);
        Option option22 = createOption(4,"sms 200",200,300);
        option12.getRequiredOption().add(option11);
        option22.getRequiredOption().add(option21);
        when(optionDAO.get(1)).thenReturn(option11);
        when(optionDAO.get(2)).thenReturn(option12);
        when(optionDAO.get(3)).thenReturn(option21);
        when(optionDAO.get(4)).thenReturn(option22);
        option11.getInconsistentOption().add(option21);
        Assert.assertFalse(optionService.isOptionsConsistentIncludingAllRequired(2, 4));
        option11.getInconsistentOption().remove(option21);
        Assert.assertTrue(optionService.isOptionsConsistentIncludingAllRequired(2, 4));
    }

    @Test
    public void isOptionIncludingAllRequiredConsistentWithSetTest(){
        Option option11 = createOption(1,"sms Пакет1",100,200);
        Option option12 = createOption(2,"sms 100",200,300);
        Option option21 = createOption(3,"sms Пакет2",100,200);
        Option option22 = createOption(4,"sms 200",200,300);
        Option option31 = createOption(5,"sms 300",200,300);
        option12.getRequiredOption().add(option11);
        option22.getRequiredOption().add(option21);
        option11.getInconsistentOption().add(option21);

        when(optionDAO.get(1)).thenReturn(option11);
        when(optionDAO.get(2)).thenReturn(option12);
        when(optionDAO.get(3)).thenReturn(option21);
        when(optionDAO.get(4)).thenReturn(option22);
        when(optionDAO.get(5)).thenReturn(option31);
        Set<OptionDTO> optionDTOs = new HashSet<OptionDTO>();
        optionDTOs.add(OptionMapper.EntityToDTOWithSet(option22));
        optionDTOs.add(OptionMapper.EntityToDTOWithSet(option31));
        Assert.assertFalse(optionService.isOptionIncludingAllRequiredConsistentWithSet(2, optionDTOs));
        option11.getInconsistentOption().remove(option21);
        Assert.assertTrue(optionService.isOptionIncludingAllRequiredConsistentWithSet(2, optionDTOs));

    }

    @Test
    public void deleteTest() {
        Integer optionId = 1;
        Option option1 = createOption(optionId, "sms100", 100, 200);
        optionService.delete(optionId);
        verify(optionDAO,times(1)).delete(optionId);
    }

    @Test
    public void removeDependencyTest() {
        Integer baseOptionId = 1;
        Integer dependentOptionId = 2;
        Option baseOption = createOption(baseOptionId, "sms Пакет", 100, 200);
        Option dependentOption = createOption(dependentOptionId, "sms200", 100, 200);
        when(optionDAO.get(baseOptionId)).thenReturn(baseOption);
        when(optionDAO.get(dependentOptionId)).thenReturn(dependentOption);

        baseOption.getDependentOption().add(dependentOption);

        optionService.removeDependency(baseOptionId,dependentOptionId);

        Assert.assertTrue(baseOption.getDependentOption().isEmpty());
        verify(optionDAO,times(1)).update(baseOption);
    }

    @Test(expected = OptionInconsistencyImpossibleException.class)
    public void isInconsistencyPossibleTest_false() throws Exception{
        Option option11 = createOption(1,"sms Пакет1",100,200);
        Option option12 = createOption(2,"sms 100",200,300);
        Option option21 = createOption(3,"sms Пакет2",100,200);
        when(optionDAO.get(1)).thenReturn(option11);
        when(optionDAO.get(2)).thenReturn(option12);
        when(optionDAO.get(3)).thenReturn(option21);
        option11.getDependentOption().add(option12);
        option21.getDependentOption().add(option12);
        optionService.addInconsistency(1, 3);

        option11.getDependentOption().remove(option12);
        option21.getDependentOption().remove(option12);
        optionService.addInconsistency(1, 3);
        Assert.assertTrue(option11.getInconsistentOption().contains(option21));
        Assert.assertTrue(option21.getInconsistentOption().contains(option11));
        verify(optionDAO,times(1)).update(option11);
        verify(optionDAO,times(1)).update(option21);
    }

    @Test
    public void isInconsistencyPossibleTest_true() throws Exception{
        Option option11 = createOption(1,"sms Пакет1",100,200);
        Option option12 = createOption(2,"sms 100",200,300);
        Option option21 = createOption(3,"sms Пакет2",100,200);
        when(optionDAO.get(1)).thenReturn(option11);
        when(optionDAO.get(2)).thenReturn(option12);
        when(optionDAO.get(3)).thenReturn(option21);
        option11.getDependentOption().add(option12);

        optionService.addInconsistency(1, 3);

        Assert.assertTrue(option11.getInconsistentOption().contains(option21));
        Assert.assertTrue(option21.getInconsistentOption().contains(option11));
        verify(optionDAO,atLeastOnce()).update(option11);
        verify(optionDAO,atLeastOnce()).update(option21);
    }

    @Test
    public void removeInconsistency() throws Exception {
        Integer opt1Id = 1;
        Integer opt2Id = 2;
        Option option11 = createOption(opt1Id,"sms Пакет1",100,200);
        Option option21 = createOption(opt2Id,"sms Пакет2",100,200);
        when(optionDAO.get(opt1Id)).thenReturn(option11);
        when(optionDAO.get(opt2Id)).thenReturn(option21);
        option11.getInconsistentOption().add(option21);
        option21.getInconsistentOption().add(option11);
        optionService.removeInconsistency(opt1Id, opt2Id);
        Assert.assertFalse(option11.getInconsistentOption().contains(option21));
        Assert.assertFalse(option21.getInconsistentOption().contains(option11));

    }

    @Test(expected = CycleInOptionsDependencyException.class)
    public void addDependencyTest_cycle() throws Exception {
        Integer opt1Id = 1;
        Integer opt2Id = 2;
        Integer opt3Id = 3;
        Option option1 = createOption(opt1Id,"sms 1",100,200);
        Option option2 = createOption(opt2Id,"sms 2",200,300);
        Option option3 = createOption(opt3Id,"sms 3", 100,200);
        when(optionDAO.get(opt1Id)).thenReturn(option1);
        when(optionDAO.get(opt2Id)).thenReturn(option2);
        when(optionDAO.get(opt3Id)).thenReturn(option3);
        option1.getDependentOption().add(option2);
        option2.getDependentOption().add(option3);
        optionService.addDependency(opt3Id, opt1Id);
    }

    @Test
    public void addDependencyTest_allok() throws Exception {
        Integer opt1Id = 1;
        Integer opt2Id = 2;
        Option option1 = createOption(opt1Id,"sms 1",100,200);
        Option option2 = createOption(opt2Id,"sms 2",200,300);
        when(optionDAO.get(opt1Id)).thenReturn(option1);
        when(optionDAO.get(opt2Id)).thenReturn(option2);
        optionService.addDependency(opt1Id, opt2Id);
        Assert.assertTrue(option1.getDependentOption().contains(option2));
        verify(optionDAO).update(option1);
    }

    @Test(expected = InconsistentOptionDependency.class)
    public void addDependencyTest_cantBeAddedDueToExistingInconsistency()
            throws Exception {
        Integer opt1Id = 1;
        Integer opt2Id = 2;
        Integer opt3Id = 3;
        Option option1 = createOption(opt1Id,"sms 1",100,200);
        Option option2 = createOption(opt2Id,"sms 2",200,300);
        Option option3 = createOption(opt3Id,"sms 3", 100,200);
        when(optionDAO.get(opt1Id)).thenReturn(option1);
        when(optionDAO.get(opt2Id)).thenReturn(option2);
        when(optionDAO.get(opt3Id)).thenReturn(option3);

        option1.getDependentOption().add(option2);
        option2.getRequiredOption().add(option1);

        option1.getInconsistentOption().add(option3);
        option3.getInconsistentOption().add(option1);

        optionService.addDependency(opt2Id, opt3Id);
    }


    public static Option createOption(Integer optionId, String title,
                                      Integer activationCharge,
                                      Integer monthlyCost){
        Option option = new Option();
        option.setOptionId(optionId);
        option.setTitle(title);
        option.setActivationCharge(activationCharge);
        option.setMonthlyCost(monthlyCost);
        option.setDependentOption(new HashSet<Option>());
        option.setRequiredOption(new HashSet<Option>());
        option.setInconsistentOption(new HashSet<Option>());
        return option;
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public OptionService optionService() {
            return new OptionServiceImpl();
        }

        @Bean
        public OptionDAO optionDAO() {
            OptionDAO optionDAO = mock(OptionDAO.class);
            Option option = new Option();

//
//            when(userDao.get(USER_ID)).thenReturn(user);
//            when(userDao.get(NOT_EXIST_USER_ID)).thenReturn(null);
//            when(userDao.getAll()).thenReturn(allUsers);

            return optionDAO;
        }



    }
}
