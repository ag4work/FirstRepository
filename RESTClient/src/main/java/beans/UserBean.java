package beans;

import dto.ContractDTO;
import dto.TariffDTO;
import org.apache.log4j.Logger;
import util.pdf.PdfUtil;

import java.io.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable{
	Logger logger = Logger.getLogger(UserBean.class);

	private Client client = ClientBuilder.newClient();

	private Integer chosenTariffId;

	private Map<Integer, String> tariffTitleMap = new HashMap<Integer, String>();

	public Integer getChosenTariffId() {
		return chosenTariffId;
	}


	public void setChosenTariffId(Integer chosenTariffId) {
		this.chosenTariffId = chosenTariffId;
	}

	public Set<TariffDTO> getTariffs(){
		//todo make a constant
		Set<TariffDTO> tariffs = Collections.emptySet();
		try {
			 tariffs = client.target("http://localhost:8080/eCareIS/rest/tariffs").
					request(MediaType.APPLICATION_JSON_TYPE).
					get(new GenericType<Set<TariffDTO>>() {
					});
			if (chosenTariffId == null) {
				for (TariffDTO tariffDTO : tariffs) {
					setChosenTariffId(tariffDTO.getTariffId());
					break;
				}
			}
			tariffTitleMap.clear();
			for (TariffDTO tariffDTO : tariffs) {
				tariffTitleMap.put(tariffDTO.getTariffId(), tariffDTO.getTitle());
			}
		} catch (Exception e){

		}
		return tariffs;
	}

	public Set<ContractDTO> getTariffContracts(){
		//todo make a constant
		Set<ContractDTO> contractDTOs = Collections.emptySet();
		try {
			contractDTOs = client.target("http://localhost:8080/"
					+ "eCareIS/rest/contractsByTariff/tariff/" + getChosenTariffId()).
					request(MediaType.APPLICATION_JSON_TYPE).
					get(new GenericType<Set<ContractDTO>>() {
					});
		} catch (Exception e) {

		}
		return contractDTOs;
	}

	public void tariffChangeValue(ValueChangeEvent e){
		System.out.println(e.getNewValue());
		setChosenTariffId((Integer) e.getNewValue());

	}

	public void printId(){
		System.out.println(getChosenTariffId());
	}

	public void downloadPDF() throws IOException {
		File pdfFile = PdfUtil.createPDF(getTariffContracts(), tariffTitleMap.get(chosenTariffId) );
		PdfUtil.uploadPDFToClient(pdfFile);
		try {
			pdfFile.delete();
		} catch (Exception e) {
			logger.warn("Error while deleting file" + pdfFile, e);
		}

	}


}
