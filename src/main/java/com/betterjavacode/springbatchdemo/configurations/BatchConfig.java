package com.betterjavacode.springbatchdemo.configurations;


import com.betterjavacode.springbatchdemo.configurations.processor.EmployeeDtoFieldSetMapper;
import com.betterjavacode.springbatchdemo.configurations.processor.EmployeeItemProcessor;
import com.betterjavacode.springbatchdemo.configurations.processor.JobCompletionListener;
import com.betterjavacode.springbatchdemo.dtos.EmployeeDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;



@Configuration
@EnableBatchProcessing
public class BatchConfig
{

    private String[] format = new String[]{"employeeId","firstName","lastName","jobTitle","email","companyId"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EmployeeDtoFieldSetMapper employeeDtoFieldSetMapper;


    @Bean
    public Job processJob(Step step)
    {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step).end().build();
    }

    @Bean
    public Step orderStep1(JdbcBatchItemWriter<EmployeeDto> writer)
    {
        return stepBuilderFactory.get("orderStep1").<EmployeeDto, EmployeeDto> chunk(10)
                .reader(flatFileItemReader())
                .processor(employeeItemProcessor())
                .writer(writer).build();
    }

    @Bean
    public FlatFileItemReader<EmployeeDto> flatFileItemReader()
    {
        return new FlatFileItemReaderBuilder<EmployeeDto>()
                .name("flatFileItemReader")
                .resource(new ClassPathResource("input/employeedata.csv"))
                .delimited()
                .names(format)
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<EmployeeDto>(){{
                    setTargetType(EmployeeDto.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<EmployeeDto> lineMapper()
    {
        final DefaultLineMapper<EmployeeDto> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(format);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(employeeDtoFieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public EmployeeItemProcessor employeeItemProcessor()
    {
        return new EmployeeItemProcessor();
    }

    @Bean
    public JobExecutionListener listener()
    {
        return new JobCompletionListener();
    }

    @Bean
    public JdbcBatchItemWriter<EmployeeDto> writer(final DataSource dataSource)
    {
        return new JdbcBatchItemWriterBuilder<EmployeeDto>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO employee(employeeId, firstName, lastName, jobTitle, email, " +
                        "companyId) VALUES(:employeeId, :firstName, :lastName, :jobTitle, :email," +
                        " " +
                        ":companyId)")
                .dataSource(dataSource)
                .build();
    }
}
